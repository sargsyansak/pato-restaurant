package com.example.patorestaurant.controller;

import com.example.patorestaurant.model.Blog;
import com.example.patorestaurant.model.Comment;
import com.example.patorestaurant.repository.BlogRepository;
import com.example.patorestaurant.repository.CommentRepository;
import com.example.patorestaurant.repository.GalleryCategoriesRepository;
import com.example.patorestaurant.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private GalleryCategoriesRepository galleryCategoriesRepository;


    @PostMapping("/comment")
    public String comment(@ModelAttribute Comment comment, ModelMap modelMap, @RequestParam(value = "blogItems", required = false) String checkboxValue) {
        Integer id = Integer.parseInt(checkboxValue);
        Optional<Blog> byId = blogRepository.findById(id);
        if (byId.isPresent()) {
            comment.setBlog(byId.get());
        }
        System.out.println(checkboxValue);

        commentRepository.save(comment);

        modelMap.addAttribute("menues", menuRepository.findAll());
        modelMap.addAttribute("galleryCategories", galleryCategoriesRepository.findAll());
        return "redirect:/adminPage";

    }
}
