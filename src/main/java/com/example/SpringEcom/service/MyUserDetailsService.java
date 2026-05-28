package com.example.SpringEcom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringEcom.model.User;
import com.example.SpringEcom.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> user = repo.findByEmail(username);
	
	    if (user.isEmpty()) {
		    System.out.println("User 404" + username);
		    throw new UsernameNotFoundException("User 404: " + username);
	    }

        User finalUser = user.get();
		 return new UserPrincipal(finalUser);
	}
}


