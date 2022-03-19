package com.markovandkrivonosov.blps.module.requested;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GrantRequestDto {

    @NotNull
    Long id;

    @NotNull
    Boolean acceptable;

    String message;

}
