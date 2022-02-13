package com.markovandkrivonosov.blps.module;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Advertisement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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
    private Brand brand;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
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
    private Region region;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @NotNull
    @Column(name = "phone")
    private Integer phone;

    @Column(name = "allow_questions")
    private Boolean allowQuestions;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Lob
    @Column(name = "image")
    private byte[] image;

}
