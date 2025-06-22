package com.tvs.microsite.dto;

public class VoteRequest {
    private String voterId;

    public VoteRequest() {}

    public VoteRequest(String voterId) {
        this.voterId = voterId;
    }

    public String getVoterId() {
        return voterId; // Ensure this method is present
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }
}
