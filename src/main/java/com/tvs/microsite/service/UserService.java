package com.tvs.microsite.service;

import com.tvs.microsite.entity.User;
import com.tvs.microsite.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> saveUser (User user) {
        // Check for duplicate email
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists!"));
        }
        // Check for duplicate phone
        if (userRepository.existsByPhone(user.getPhone())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Phone number already exists!"));
        }
        // Save if no duplicates
        User savedUser  = userRepository.save(user);
        return ResponseEntity.ok(savedUser );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<?> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found with id: " + id));
        }
    }
}
