package com.tvs.microsite.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dealers")
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contactNumber;
    private double latitude;
    private double longitude;

    @Column(columnDefinition = "integer default 0")
    private int votes;
}
