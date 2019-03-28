package com.example.patorestaurant.repository;

import com.example.patorestaurant.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
}
