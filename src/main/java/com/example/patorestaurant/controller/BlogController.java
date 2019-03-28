package com.example.patorestaurant.controller;

import com.example.patorestaurant.model.Blog;
import com.example.patorestaurant.model.Comment;
import com.example.patorestaurant.repository.BlogRepository;
import com.example.patorestaurant.repository.CommentRepository;
import com.example.patorestaurant.repository.GalleryCategoriesRepository;
import com.example.patorestaurant.repository.MenuRepository;
import com.example.patorestaurant.security.SpringUser;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Controller
public class BlogController {
    @Value("${image.upload.dir}")
    private String imageUploadDir;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private GalleryCategoriesRepository galleryCategoriesRepository;
    @Autowired
    private CommentRepository commentRepository;


    @PostMapping("/blog")
    public String blog(@ModelAttribute Blog blog, @RequestParam("picBlog") MultipartFile file, @AuthenticationPrincipal SpringUser springUser, ModelMap modelMap) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File picture = new File(imageUploadDir + File.separator + fileName);
        file.transferTo(picture);
        blog.setBlogPicUrl(fileName);
        blog.setDate(new Date());
        blog.setUser(springUser.getUser());
        blogRepository.save(blog);
        modelMap.addAttribute("menues", menuRepository.findAll());
        modelMap.addAttribute("galleryCategories", galleryCategoriesRepository.findAll());
        return "adminPage";
    }

    @GetMapping("/blog/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("imgBlog") String picUrl) throws IOException {
        InputStream in = new FileInputStream(imageUploadDir + File.separator + picUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping("/blog/current")
    public String currentPage(ModelMap modelMap, @ModelAttribute Comment comment) {
        modelMap.addAttribute("blogs", blogRepository.findAll());
//        commentRepository.save(comment);
        return "blogCurrent";
    }
}
