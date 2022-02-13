package com.markovandkrivonosov.blps.module.requested;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequestDto {

    @NotBlank
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
