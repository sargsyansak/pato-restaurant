package com.example.patorestaurant.controller;

import com.example.patorestaurant.model.Slider;
import com.example.patorestaurant.repository.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class SliderController {

    @Value("${image.upload.dir}")
    private String imageUploadDir;
    @Autowired
    private SliderRepository sliderRepository;

    @PostMapping("/slider")
    public String slider(@ModelAttribute Slider slider, @RequestParam("picSlider") MultipartFile file, ModelMap modelMap) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File picture = new File(imageUploadDir + File.separator + fileName);
        file.transferTo(picture);
        slider.setSliderPicUrl(fileName);
        sliderRepository.save(slider);
        return "adminPage";
    }
}
