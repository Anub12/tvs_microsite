package com.tvs.microsite.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String pinCode;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer votedDealer;
}
