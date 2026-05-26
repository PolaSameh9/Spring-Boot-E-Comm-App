package com.example.SpringEcom.service;

import org.springframework.stereotype.Service;

import com.example.SpringEcom.model.User;
import com.example.SpringEcom.repo.UserRepo;

@Service
public class UserService {

    private UserRepo repo;

    public User saveUser(User user) {
       return repo.save(user);
    }



}
