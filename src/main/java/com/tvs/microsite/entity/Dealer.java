package com.tvs.microsite.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(name = "voter_ids", joinColumns = @JoinColumn(name = "dealer_id"))
    @Column(name = "voter_id")
    private Set<String> voterIds = new HashSet<>();

    public Set<String> getVoterIds() {
        return voterIds;
    }

    public void setVoterIds(Set<String> voterIds) {
        this.voterIds = voterIds;
    }
}
