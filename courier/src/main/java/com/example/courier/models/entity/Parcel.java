package com.example.courier.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Parcel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "parcel_sequence"
    )
    @SequenceGenerator(
            name = "parcel_sequence",
            sequenceName = "parcel_sequence",
            allocationSize = 1
    )
    private Long id;
    private Date dateToDelivery;

    private String name;
    private float length;
    private float width;
    private float height;

    private float weight;

}
