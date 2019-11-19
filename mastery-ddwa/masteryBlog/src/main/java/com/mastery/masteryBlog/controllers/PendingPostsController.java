/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.controllers;

import com.mastery.masteryBlog.dtos.Category;
import com.mastery.masteryBlog.dtos.Post;
import com.mastery.masteryBlog.dtos.Tags;
import com.mastery.masteryBlog.service.CategoryService;
import com.mastery.masteryBlog.service.PostService;
import com.mastery.masteryBlog.service.TagService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author corey
 */
@Controller
public class PendingPostsController {
    
    @Autowired
    CategoryService cService;
    
    @Autowired
    PostService pService;
    
    @Autowired
    TagService tService;
    
    List<Post> staticList;
    
     private static String UPLOADED_FOLDER = "src/main/resources/static/images/";
    

    @GetMapping("pending-posts")
    public String displayHomePage(Model model) {
        List<Post> posts = pService.getAllPosts();
        List<Category> categories = cService.getAllCatagories();
        List<Tags> tags = tService.getAllTags();
        staticList = pService.getAllStaticPost();
        model.addAttribute("Static", staticList);
        model.addAttribute("Tags", tags);
        model.addAttribute("Posts", posts);
        model.addAttribute("Categories", categories);
        return "pending-posts";
    }
    
    @GetMapping("deletePending")
    public String deletePost(HttpServletRequest request) {
       int id = Integer.parseInt(request.getParameter("id"));
       tService.deletePost(id);
       pService.deletePost(id);
       return "redirect:/pending-posts";
    }
    
        @PostMapping("uploadPost") 
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes, HttpServletRequest request) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:pending-post";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            String pathString = path.toString();
            String fixedPath = pathString.substring(25);

            int postId = Integer.parseInt(request.getParameter("postId"));
            pService.addImage(fixedPath, postId);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        return "redirect:/pending-post";
        
    }
}
