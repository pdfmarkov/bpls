package com.markovandkrivonosov.blps.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "region",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Region {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<City> cities;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Advertisement> advertisements;

}