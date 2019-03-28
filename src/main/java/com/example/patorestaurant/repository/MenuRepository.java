package com.example.patorestaurant.repository;

import com.example.patorestaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findByName(String name);

}
