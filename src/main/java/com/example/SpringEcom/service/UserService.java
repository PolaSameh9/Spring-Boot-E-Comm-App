package com.example.SpringEcom.service;

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
        repo.save(user);
        return "User added successfully!";
    }



}
