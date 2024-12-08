package com.tvs.microsite.repository;

import com.tvs.microsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // that's it we have to write nothing here
    // JpaRepository will do the work automatically
}
