package com.tvs.microsite.service;

import com.tvs.microsite.dto.VoteRequest;
import com.tvs.microsite.entity.Dealer;
import com.tvs.microsite.repository.DealerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DealerService {
    private final DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public List<Dealer> findNearbyDealers(double latitude, double longitude, double radius) {
        double offset = radius / 111; // Approximation for degrees within a radius
        return dealerRepository.findByLatitudeBetweenAndLongitudeBetween(
                latitude - offset, latitude + offset,
                longitude - offset, longitude + offset);
    }

    public Dealer saveDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }

    @Transactional
    public Dealer voteForDealer(Long dealerId, String voterId) {
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new RuntimeException("Dealer not found with id: " + dealerId));

        // Check if user has already voted
        if (dealer.getVoterIds().contains(voterId)) {
            throw new RuntimeException("You have already voted for this dealer");
        }

        dealer.setVotes(dealer.getVotes() + 1);
        dealer.getVoterIds().add(voterId);
        return dealerRepository.save(dealer);
    }

    public List<Dealer> searchDealers(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return dealerRepository.findAll();
        }
        return dealerRepository.searchDealers(searchTerm.trim());
    }

}
