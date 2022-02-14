package com.markovandkrivonosov.blps.module.requested;

import com.markovandkrivonosov.blps.module.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class AdvertisementRequestDto implements Serializable {
    @NotBlank
    @Size(min = 17, max = 17)
    private final String vin;
    @Size(min = 10, max = 10, message = "СТС состоит из 10 символов!")
    private final String sts;
    private final String stateNumber;
    @NotNull
    private final String brandName;
    @NotNull
    private final String modelName;
    @NotNull
    private final Integer mileage;
    @NotNull
    private final Boolean isRight;
    @NotNull
    private final Body body;
    @NotNull
    private final Integer releaseYear;
    private final String driveUnit;
    private final Fuel fuel;
    private final Boolean isAuto;
    private final Color color;
    private final Boolean hasDocuments;
    private final Boolean needsRepair;
    private final String description;
    @NotNull
    private final Integer price;
    private final Boolean exchangePossible;
    @NotNull
    private final Status status;
    @NotNull
    private final String regionName;
    @NotNull
    private final String cityName;
    @NotNull
    @Pattern(regexp="(^$|[0-9]{10})")
    private final String phone;
    private final Boolean allowQuestions;
    private final Boolean isPaid;
    private final byte[] image;
}
