package com.tvs.microsite.controller;

import com.tvs.microsite.entity.Dealer;
import com.tvs.microsite.service.DealerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealers")
@CrossOrigin(origins = "http://localhost") // Allow frontend access
public class DealerController {
    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    // Add new dealer endpoint
    @PostMapping("/add")
    public ResponseEntity<Dealer> addDealer(@RequestBody Dealer dealer) {
        Dealer savedDealer = dealerService.saveDealer(dealer);
        return ResponseEntity.ok(savedDealer);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<Dealer>> findNearbyDealers(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "10") double radius) {
        List<Dealer> dealers = dealerService.findNearbyDealers(latitude, longitude, radius);
        return ResponseEntity.ok(dealers);
    }

    @PostMapping
    public ResponseEntity<Dealer> createDealer(@RequestBody Dealer dealer) {
        Dealer savedDealer = dealerService.saveDealer(dealer);
        return ResponseEntity.ok(savedDealer);
    }

    @GetMapping
    public ResponseEntity<List<Dealer>> getAllDealers() {
        List<Dealer> dealers = dealerService.getAllDealers();
        return ResponseEntity.ok(dealers);
    }

    @PostMapping("/{dealerId}/vote")
    public void voteForDealer(@PathVariable Long dealerId, @RequestParam String userId) {
        dealerService.voteForDealer(dealerId, userId);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Dealer>> searchDealers(@RequestParam(required = false) String query) {
        List<Dealer> dealers = dealerService.searchDealers(query);
        return ResponseEntity.ok(dealers);
    }
}
