package com.example.patorestaurant.repository;

import com.example.patorestaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    MenuItem findByName(String name);
}
