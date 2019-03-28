package com.example.patorestaurant.repository;

import com.example.patorestaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}

