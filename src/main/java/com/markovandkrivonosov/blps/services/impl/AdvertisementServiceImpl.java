package com.markovandkrivonosov.blps.services.impl;

import com.markovandkrivonosov.blps.exceptions.ResourceNotFoundException;
import com.markovandkrivonosov.blps.module.Advertisement;
import com.markovandkrivonosov.blps.module.User;
import com.markovandkrivonosov.blps.module.requested.GrantRequestDto;
import com.markovandkrivonosov.blps.repo.AdvertisementRepository;
import com.markovandkrivonosov.blps.services.AdvertisementService;
import com.markovandkrivonosov.blps.services.EmailService;
import com.markovandkrivonosov.blps.services.UserService;
import com.markovandkrivonosov.blps.specifications.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Override
    public List<Advertisement> findAllAdvertisementsBySpecification(Map<String, String> values, Boolean isAcceptable) {
        return advertisementRepository.findAll(Specification
                        .where(new AdvertisementWithBrandName(values.get("brand")))
                        .and(new AdvertisementWithCityName(values.get("city")))
                        .and(new AdvertisementWithRegionName(values.get("region")))
                        .and(new AdvertisementWithAcceptable(isAcceptable)),
                PageRequest.of(Integer.parseInt(values.get("page")),
                        Integer.parseInt(values.get("size")))).toList();
    }

    @Override
    public Optional<Advertisement> findAdvertisementById(Long id) {
        return advertisementRepository.findById(id);
    }

    @Override
    public Advertisement saveAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public List<Advertisement> findAllAdvertisementByEmail(String email) {
        return advertisementRepository.findAllByUserEmail(email);
    }

    @Override
    @Transactional
    public void grantAdvertisementWithEmail(GrantRequestDto grantRequestDto) {
        Advertisement advertisement = findAdvertisementById(grantRequestDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Error: Ad Not Found"));

        advertisement.setAcceptable(grantRequestDto.getAcceptable());

        advertisement = saveAdvertisement(advertisement);

        String emailMessage = advertisement.getAcceptable() ? "прошла успешно! Проверяйте на доске объявлений!" : "не одобрена((";

        String adminMessage = grantRequestDto.getMessage() == null || grantRequestDto.getMessage().trim().equals("")
                ? "" : "Комментарий модератора: " + grantRequestDto.getMessage();

        emailService.sendSimpleMessage(advertisement.getUser().getEmail(),
                "DROM.RU - Изменился статус объявления",
                "Здравствуйте, " + advertisement.getUser().getName() + "!\n" +
                        "Ваша заявка прошла модерацию!\n" +
                        "Заявка по " + advertisement.getVin() + " " + emailMessage  + "\n" +
                        adminMessage  + "\n" +
                        "Спасибо!");

    }

    @Override
    @Transactional
    public void addNewAdvertisementWithEmail(Advertisement advertisement) {
        User user = userService.findUserByEmail(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Error: User Not Found"));

        user.getAdvertisements().add(advertisement);
        advertisement.setUser(user);

        user = userService.saveUser(user);

        List<User> administrators = userService.findAllUserByRole("ROLE_ADMIN");

        for (User administrator : administrators) {
            emailService.sendSimpleMessage(administrator.getEmail(),
                    "DROM.RU - Новое объявление",
                    "Здравствуйте, " + administrator.getName() + "!\n" +
                            "Вам пришло новое объявление на модерацию!\n" +
                            "Id этого объявления: " + user.getAdvertisements().stream().filter(ad -> ad.getVin().equals(advertisement.getVin())).findAny()
                            .orElseThrow(() -> new ResourceNotFoundException("Error: Ad Not Found")).getId() + "\n" +
                            "Спасибо!");
        }
    }

}
