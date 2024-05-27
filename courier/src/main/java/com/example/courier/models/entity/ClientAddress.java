package com.example.courier.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ClientAddress {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_address_sequence"
    )
    @SequenceGenerator(
            name = "client_address_sequence",
            sequenceName = "client_address_sequence",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne
    private Client sender;

    @ManyToOne
    private Address pickUpPoint;

    @ManyToOne
    private Address deliveryPoint;
}
