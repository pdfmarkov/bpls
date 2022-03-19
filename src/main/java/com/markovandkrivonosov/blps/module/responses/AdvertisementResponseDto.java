package com.markovandkrivonosov.blps.module.responses;

import com.markovandkrivonosov.blps.module.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementResponseDto implements Serializable, Comparable<AdvertisementResponseDto> {
    @NotBlank(message = "VIN не должен быть пустым!")
    @Size(min = 17, max = 17, message = "VIN должен состоять из 17 символов!")
    private String vin;
    @Size(min = 10, max = 10, message = "СТС должен состоять из 10 символов!")
    private String sts;
    private String stateNumber;
    @NotBlank(message = "Brand не должен быть пустым!")
    private String brandName;
    @NotBlank(message = "Model не должен быть пустым!")
    private String modelName;
    @NotNull(message = "Mileage не должен быть пустым!")
    private Integer mileage;
    @NotNull(message = "isRight не должен быть пустым!")
    private Boolean isRight;
    @NotNull(message = "Body не должен быть пустым!")
    private Body body;
    @NotNull(message = "ReleaseYear не должен быть пустым!")
    private Integer releaseYear;
    private DriveUnit driveUnit;
    private Fuel fuel;
    private Boolean isAuto;
    private Color color;
    private Boolean hasDocuments;
    private Boolean needsRepair;
    private String description;
    @NotNull(message = "Price не должен быть пустым!")
    private Integer price;
    private Boolean exchangePossible;
    @NotNull(message = "Status не должен быть пустым!")
    private Status status;
    @NotNull(message = "Region не должен быть пустым!")
    private String regionName;
    @NotNull(message = "City не должен быть пустым!")
    private String cityName;
    @NotNull(message = "phone не должен быть пустым!")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Phone должен быть номером телефона!")
    private String phone;
    private Boolean allowQuestions;
    private Boolean isPaid;
    private byte[] image;

    @Override
    public int compareTo(AdvertisementResponseDto advertisementResponseDto) {
        return brandName.compareTo(advertisementResponseDto.getBrandName());
    }
}
