//package com.example.courier.models.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//public class Locality {
//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "locality_sequence"
//    )
//    @SequenceGenerator(
//            name = "locality_sequence",
//            sequenceName = "locality_sequence",
//            allocationSize = 1
//    )
//    private Long id;
//    private String name;
//    private Long localityTypeId;
//
//    @ManyToOne
//    private Locality parentLocality;
//}
