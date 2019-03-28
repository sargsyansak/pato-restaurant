package com.example.patorestaurant.controller;

import com.example.patorestaurant.model.Gallery;
import com.example.patorestaurant.model.GalleryCategories;
import com.example.patorestaurant.repository.GalleryCategoriesRepository;
import com.example.patorestaurant.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class GalleryController {
    @Value("${image.upload.dir}")
    private String imageUploadDir;
    @Autowired
    private GalleryCategoriesRepository galleryCategoriesRepository;
    @Autowired
    private GalleryRepository galleryRepository;

    @PostMapping("/galleryCategories")
    public String categories(@ModelAttribute GalleryCategories galleryCategories, ModelMap modelMap) throws IOException {
        if (galleryCategoriesRepository.findByName(galleryCategories.getName()) == null) {
            galleryCategoriesRepository.save(galleryCategories);
            return "redirect:/adminPage";
        } else {
            modelMap.addAttribute("messageGalleryCategories", "Categories with this name is already exist");
            return "adminPage";
        }
    }

    @PostMapping("/gallery")
    public String menuItem(@ModelAttribute Gallery gallery, @RequestParam("picture") MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File picture = new File(imageUploadDir + File.separator + fileName);
        file.transferTo(picture);
        gallery.setGalleryPicUrl(fileName);
        galleryRepository.save(gallery);
        return "redirect:/adminPage";
    }
}
