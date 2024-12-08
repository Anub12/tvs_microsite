package com.tvs.microsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsService {
    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GoogleMapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGeocode(String pinCode) {
        String url = String.format(
                "https://maps.google.com/maps/api/geocode/json?address=%s&key=%s",
                pinCode, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
}
