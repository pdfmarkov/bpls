package com.markovandkrivonosov.blps.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "city",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @JsonIgnore
    private Region region;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Advertisement> advertisements;

}
