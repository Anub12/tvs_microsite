package com.tvs.microsite.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsService {
    @Value("${google.maps.api.key:your-google-maps-api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GoogleMapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGeocode(String pinCode) {
        // Fixed URL: was "maps.google.com" now "maps.googleapis.com"
        String url = String.format(
                "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",
                pinCode, apiKey);
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Error fetching geocode: " + e.getMessage();
        }
    }
}
