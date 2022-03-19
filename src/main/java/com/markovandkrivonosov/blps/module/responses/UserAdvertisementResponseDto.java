package com.markovandkrivonosov.blps.module.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAdvertisementResponseDto extends AdvertisementResponseDto {

    Boolean acceptable;

}
