package com.markovandkrivonosov.blps.module.responses;

import com.markovandkrivonosov.blps.module.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminAdvertisementResponseDto extends AdvertisementResponseDto {

    Boolean acceptable;
    Long id;

}
