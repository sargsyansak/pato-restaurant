package com.example.patorestaurant.controller;

import com.example.patorestaurant.model.Employee;
import com.example.patorestaurant.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class EmployeeController {
    @Value("${image.upload.dir}")
    private String imageUploadDir;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public String employee(@RequestParam("ratingg") String rat, @ModelAttribute Employee employee, @RequestParam("picEmployee") MultipartFile file) throws IOException {

        System.out.println("rate" + rat);
        if (rat.equals("")) {
            employee.setRating(0.0);
        } else {
            try {
                Double rate = Double.valueOf(rat);
                employee.setRating(rate);
            }catch (NumberFormatException e){
                employee.setRating(0.0);
            }
        }
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File picture = new File(imageUploadDir + File.separator + fileName);
        file.transferTo(picture);
        employee.setEmployeePicUrl(fileName);
        employeeRepository.save(employee);
        return "adminPage";
    }
}
