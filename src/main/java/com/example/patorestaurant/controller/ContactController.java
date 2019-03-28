package com.example.patorestaurant.controller;

import com.example.patorestaurant.model.Contact;
import com.example.patorestaurant.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Value("${image.upload.dir}")
    private String imageUploadDir;
    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/contact")
    public String contact(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return "adminPage";

    }
}
