package com.example.SpringEcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringEcom.model.AuthRequest;
import com.example.SpringEcom.model.User;
import com.example.SpringEcom.service.JwtService;
import com.example.SpringEcom.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getEmail());
        }
        else{
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
