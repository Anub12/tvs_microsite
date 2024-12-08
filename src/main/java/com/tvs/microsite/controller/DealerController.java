package com.tvs.microsite.controller;

import com.tvs.microsite.entity.Dealer;
import com.tvs.microsite.service.DealerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dealers")
public class DealerController {
    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    public List<Dealer> findNearbyDealers(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "10") double radius) {
        return dealerService.findNearbyDealers(latitude, longitude, radius);
    }

    public void voteForDealer(@PathVariable Long id) {
        dealerService.voteForDealer(id);
    }
}
