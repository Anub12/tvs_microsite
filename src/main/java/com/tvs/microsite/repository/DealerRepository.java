package com.tvs.microsite.repository;

import com.tvs.microsite.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
    List<Dealer> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLon, double maxLon);

    @Query("SELECT d FROM Dealer d WHERE " +
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.address) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.contact) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Dealer> searchDealers(@Param("searchTerm") String searchTerm);
}
