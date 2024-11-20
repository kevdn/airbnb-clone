package com.kevdn.airbnb.domain.homestay.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "amenity", schema = "airbnb")
@RequiredArgsConstructor
@Getter
@Setter
public class Amenity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "amenities",
            fetch = FetchType.LAZY
    )
    private Set<Homestay> homestays;
}
