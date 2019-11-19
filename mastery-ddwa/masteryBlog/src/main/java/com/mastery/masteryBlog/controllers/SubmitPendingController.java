/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.controllers;

import com.mastery.masteryBlog.dtos.Category;
import com.mastery.masteryBlog.dtos.Post;
import com.mastery.masteryBlog.service.CategoryService;
import com.mastery.masteryBlog.service.PostService;
import com.mastery.masteryBlog.service.TagService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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
public class SubmitPendingController {
    
   @Autowired
   PostService pService;
   
   @Autowired
   CategoryService cService;
   
   @Autowired
   TagService tService;
   
   List<Post> staticList;
   
   private static String UPLOADED_FOLDER = "src/main/resources/static/images/";
    
    
    @GetMapping("submit-pending")
    public String editPending(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Post post = pService.getPostById(id);
        List<Category> Catagories = cService.getAllCatagories();
        staticList = pService.getAllStaticPost();
        model.addAttribute("Static", staticList);
        model.addAttribute("Categories", Catagories);
        model.addAttribute("post", post);
        
        return "submit-pending";
    }
    
    @PostMapping("submitPending")
    public String confirmSubmit(HttpServletRequest request, Post submitPost, Boolean approved){
        
        
    LocalDateTime date = LocalDateTime.now();
        
      
      submitPost.setPostId(Integer.parseInt(request.getParameter("postId")));
      submitPost.setUsername(request.getParameter("username"));
      submitPost.setDateTime(date);
      submitPost.setTitle(request.getParameter("title"));
      submitPost.setCategory(cService.getCatagoryById(Integer.parseInt(request.getParameter("catagories"))));
      submitPost.setPosting(request.getParameter("posting"));
      submitPost.setImagePath(request.getParameter("imagePath"));
      if(approved != null){
          submitPost.setApproved(approved);
      } else {
          submitPost.setApproved(false);
      }
      
      
      
      pService.updatePending(submitPost);
      
      
    return "redirect:/pending-posts";
    
    }
    
     @PostMapping("pendingUpload") 
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes, HttpServletRequest request) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:submit-pending";
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

        return "redirect:/submit-pending";
    }
}
