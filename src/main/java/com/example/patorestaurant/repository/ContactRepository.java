package com.example.patorestaurant.repository;

import com.example.patorestaurant.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
