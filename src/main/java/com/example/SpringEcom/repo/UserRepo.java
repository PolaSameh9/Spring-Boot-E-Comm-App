package com.example.SpringEcom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringEcom.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
