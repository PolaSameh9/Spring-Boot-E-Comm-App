package com.example.SpringEcom.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringEcom.model.User;
import com.example.SpringEcom.repo.UserRepo;

@Service
public class UserService {

    private final UserRepo repo;

    private final PasswordEncoder encoder;
    

    @Autowired
    public UserService(UserRepo repo, PasswordEncoder encoder){
        this.repo = repo;
        this.encoder = encoder;
    }

    public String saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isBlank()) {
            user.setRoles("ROLE_USER");
        }
        repo.save(user);
        return "User added successfully!";
    }

    public Optional<User> findByEmail(String email) {
       return repo.findByEmail(email);
    }

public User createOAuthUser(String email, String login) {
    User user = new User();

    if (email != null && !email.isBlank()) {
        user.setEmail(email);
        user.setUsername(email.split("@")[0]);
    } else {
        user.setEmail(login + "@github.com");
        user.setUsername(login);
    }

    user.setRoles("ROLE_USER");
    user.setPassword(encoder.encode(UUID.randomUUID().toString()));
    return repo.save(user);
}



}
