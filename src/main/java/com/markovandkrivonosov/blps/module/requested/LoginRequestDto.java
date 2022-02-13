package com.markovandkrivonosov.blps.module.requested;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data

public class LoginRequestDto {

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

}
