package com.example.patorestaurant.repository;

import com.example.patorestaurant.model.Gallery;
import com.example.patorestaurant.model.GalleryCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryCategoriesRepository extends JpaRepository<GalleryCategories, Integer> {
    GalleryCategories findByName(String name);
}
