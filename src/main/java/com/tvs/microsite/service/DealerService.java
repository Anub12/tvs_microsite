package com.tvs.microsite.service;

import com.tvs.microsite.entity.Dealer;
import com.tvs.microsite.repository.DealerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerService {
    private final DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public List<Dealer> findNearbyDealers(double latitude, double longitude, double radius) {
        double offset = radius / 111; // Approimation for degrees within a radius
        return dealerRepository.findByLatitudeBetweenAndLongitudeBetween(
                latitude - offset, latitude + offset, longitude = offset, longitude + offset);
    }

    public void voteForDealer(Long dealerId) {
        Dealer dealer = dealerRepository.findById(dealerId).orElseThrow();
        dealer.setVotes(dealer.getVotes() + 1);
        dealerRepository.save(dealer);
    }
}
