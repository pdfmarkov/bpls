package com.markovandkrivonosov.blps.controllers;


import com.markovandkrivonosov.blps.exceptions.ResourceIsAlreadyExistsException;
import com.markovandkrivonosov.blps.exceptions.ResourceNotFoundException;
import com.markovandkrivonosov.blps.exceptions.TokenRefreshException;
import com.markovandkrivonosov.blps.module.ERole;
import com.markovandkrivonosov.blps.module.RefreshToken;
import com.markovandkrivonosov.blps.module.Role;
import com.markovandkrivonosov.blps.module.User;
import com.markovandkrivonosov.blps.module.requested.LogOutRequestDto;
import com.markovandkrivonosov.blps.module.requested.LoginRequestDto;
import com.markovandkrivonosov.blps.module.requested.RefreshTokenRequestDto;
import com.markovandkrivonosov.blps.module.requested.SignupRequestDto;
import com.markovandkrivonosov.blps.module.responses.JwtResponseDto;
import com.markovandkrivonosov.blps.module.responses.MessageResponseDto;
import com.markovandkrivonosov.blps.module.responses.TokenRefreshResponseDto;
import com.markovandkrivonosov.blps.repo.RoleRepository;
import com.markovandkrivonosov.blps.repo.UserRepository;
import com.markovandkrivonosov.blps.security.jwt.JwtUtils;
import com.markovandkrivonosov.blps.security.module.UserDetailsImpl;
import com.markovandkrivonosov.blps.security.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "${cors.urls}")
@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponseDto(jwt, refreshToken.getToken(), userDetails.getId(),
                userDetails.getUsername(), roles));

    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody RefreshTokenRequestDto request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getEmail());
                    return ResponseEntity.ok(new TokenRefreshResponseDto(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDto signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new ResourceIsAlreadyExistsException("Error: Phone is already taken!");

        User user = new User(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequestDto logOutRequest) {
        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
        return ResponseEntity.ok(new MessageResponseDto("Log out successful!"));
    }
}