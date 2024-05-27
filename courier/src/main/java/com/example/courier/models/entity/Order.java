package com.example.courier.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "order_")
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne
    private Client sender;

    @ManyToOne
    private Client recipient;

    @ManyToOne
    private Address pickUpPoint;

    @ManyToOne
    private Address deliveryPoint;

    @OneToOne
    private Parcel parcel;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double orderPrice;
}
