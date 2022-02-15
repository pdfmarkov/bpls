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
    @NotBlank(message = "VIN не должен быть пустым!")
    @Size(min = 17, max = 17, message = "VIN должен состоять из 17 символов!")
    private final String vin;
    @Size(min = 10, max = 10, message = "СТС должен состоять из 10 символов!")
    private final String sts;
    private final String stateNumber;
    @NotBlank(message = "Brand не должен быть пустым!")
    private final String brandName;
    @NotBlank(message = "Model не должен быть пустым!")
    private final String modelName;
    @NotNull(message = "Mileage не должен быть пустым!")
    private final Integer mileage;
    @NotNull(message = "isRight не должен быть пустым!")
    private final Boolean isRight;
    @NotNull(message = "Body не должен быть пустым!")
    private final Body body;
    @NotNull(message = "ReleaseYear не должен быть пустым!")
    private final Integer releaseYear;
    private final DriveUnit driveUnit;
    private final Fuel fuel;
    private final Boolean isAuto;
    private final Color color;
    private final Boolean hasDocuments;
    private final Boolean needsRepair;
    private final String description;
    @NotNull(message = "Price не должен быть пустым!")
    private final Integer price;
    private final Boolean exchangePossible;
    @NotNull(message = "Status не должен быть пустым!")
    private final Status status;
    @NotNull(message = "Region не должен быть пустым!")
    private final String regionName;
    @NotNull(message = "City не должен быть пустым!")
    private final String cityName;
    @NotNull(message = "phone не должен быть пустым!")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Phone должен быть номером телефона!")
    private final String phone;
    private final Boolean allowQuestions;
    private final Boolean isPaid;
    private final byte[] image;
}
