package com.markovandkrivonosov.blps.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "advertisement",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "vin")
        })
public class Advertisement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(name = "acceptable")
    private Boolean acceptable;

    //    Example: WAUZZZ44ZEN096063
    @NotBlank
    @Size(min=17, max=17)
    @Column(name = "vin")
    private String vin;

    @Size(min=10, max=10)
    @Column(name = "sts")
    private String sts;

    @Column(name = "state_number")
    private String stateNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Brand brand;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Model model;

    @NotNull
    @Column(name = "mileage")
    private Integer mileage;

    @NotNull
    @Column(name = "is_right")
    private Boolean isRight;

    @NotNull
    @Column(name = "body")
    private Body body;

    @NotNull
    @Column(name = "release_year")
    private Integer releaseYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "drive_unit")
    private DriveUnit driveUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel")
    private Fuel fuel;

    @Column(name = "is_auto")
    private Boolean isAuto;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    @Column(name = "has_documents")
    private Boolean hasDocuments;

    @Column(name = "needs_repair")
    private Boolean needsRepair;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @Column(name = "exchange_possible")
    private Boolean exchangePossible;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Region region;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private City city;

    @NotNull
    @Pattern(regexp="(^$|[0-9]{10})")
    @Column(name = "phone")
    private String phone;

    @Column(name = "allow_questions")
    private Boolean allowQuestions;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Lob
    @Column(name = "image")
    private byte[] image;

    public Advertisement(String vin, Brand brand, Model model, Integer mileage, Boolean isRight, Body body, Integer releaseYear, Integer price, Status status, Region region, City city, String phone) {
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.isRight = isRight;
        this.body = body;
        this.releaseYear = releaseYear;
        this.price = price;
        this.status = status;
        this.region = region;
        this.city = city;
        this.phone = phone;
    }
}
