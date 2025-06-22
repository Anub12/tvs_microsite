package com.tvs.microsite.controller;

import com.tvs.microsite.dto.VoteRequest;
import com.tvs.microsite.entity.Dealer;
import com.tvs.microsite.service.DealerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dealers")
@CrossOrigin(origins = "http://localhost") // Allow frontend access
public class DealerVoteController {
    private final DealerService dealerService;

    public DealerVoteController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping("/{dealerId}/vote")
    public ResponseEntity<?> voteForDealer(
            @PathVariable Long dealerId,
            @RequestBody VoteRequest voteRequest) {
        try {
            // Use the correct getter method
            Dealer dealer = dealerService.voteForDealer(dealerId, voteRequest.getVoterId());
            return ResponseEntity.ok(dealer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
