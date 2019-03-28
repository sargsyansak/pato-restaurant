package com.example.patorestaurant.controller;

import com.example.patorestaurant.model.Menu;
import com.example.patorestaurant.model.MenuItem;
import com.example.patorestaurant.repository.GalleryCategoriesRepository;
import com.example.patorestaurant.repository.MenuItemRepository;
import com.example.patorestaurant.repository.MenuRepository;
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
public class MenuController {
    @Value("${image.upload.dir}")
    private String imageUploadDir;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private GalleryCategoriesRepository galleryCategoriesRepository;

    @PostMapping("/menu")
    public String menu(@ModelAttribute Menu menu, @RequestParam("picture") MultipartFile file, ModelMap modelMap) throws IOException {
        if (menuRepository.findByName(menu.getName()) == null) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File picture = new File(imageUploadDir + File.separator + fileName);
            file.transferTo(picture);
            menu.setMenuPicUrl(fileName);
            menuRepository.save(menu);
            return "redirect:adminPage";
        } else {
            modelMap.addAttribute("menues", menuRepository.findAll());
            modelMap.addAttribute("galleryCategories", galleryCategoriesRepository.findAll());
            modelMap.addAttribute("messageMenu", "Menu with this name is already exist");
            return "adminPage";
        }
    }

    @PostMapping("/menuItem")
    public String menuItem(@ModelAttribute MenuItem menuItem, @RequestParam("picture") MultipartFile file, ModelMap modelMap) throws IOException {
        if (menuRepository.findByName(menuItem.getName()) == null) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File picture = new File(imageUploadDir + File.separator + fileName);
            file.transferTo(picture);
            menuItem.setMenuItemPicUrl(fileName);
            menuItemRepository.save(menuItem);
            return "redirect:adminPage";
        } else {
            modelMap.addAttribute("messageItem", "MenuItem with this name is already exist");
            return "adminPage";
        }
    }
}
