/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.controllers;

import com.mastery.masteryBlog.dtos.Category;
import com.mastery.masteryBlog.dtos.Post;
import com.mastery.masteryBlog.dtos.Role;
import com.mastery.masteryBlog.dtos.Tags;
import com.mastery.masteryBlog.dtos.User;
import com.mastery.masteryBlog.service.PostService;
import com.mastery.masteryBlog.service.TagService;
import com.mastery.masteryBlog.service.UserDetailsServiceImpl;
import com.mastery.masteryBlog.service.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.mastery.masteryBlog.service.CategoryService;
import com.mastery.masteryBlog.service.RoleService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author corey
 */
@Controller
public class EmployeePostController {
    
    @Autowired
    UserDetailsServiceImpl service;
    
    @Autowired
    CategoryService cService;
    
    @Autowired
    PostService pService;
      
    @Autowired
    UserService uService;
    
    @Autowired
    RoleService rService;
    
    @Autowired
    TagService tService;
    
    List<Post> staticList;
    
    @GetMapping("/employee-post")
    public String showPage(Model model) {
        List<Category> Catagories = cService.getAllCatagories();
        staticList = pService.getAllStaticPost();
        model.addAttribute("Static", staticList);
        model.addAttribute("Catagories", Catagories);
        return "employee-post";
    }
    
    @PostMapping("addEmployeePost")
    public String addPost(HttpServletRequest request, Post post, Principal principal){
        
        String Username = principal.getName();
        User user = uService.getUserByUsername(Username);
        
        Set<Role> roles = new HashSet<>();
        roles.add(rService.getRoleByRoleName("ROLE_ADMIN"));
        roles.add(rService.getRoleByRoleName("ROLE_USER"));
        
        
        
        String tags = request.getParameter("tags");
        String[] tagList = tags.split(",");
        List<Tags> tag = new ArrayList<>();
        
        LocalDateTime date = LocalDateTime.now();
        
      post.setUsername(Username);
      post.setTitle(request.getParameter("title"));
      post.setDateTime(date);
      post.setCategory(cService.getCatagoryById(Integer.parseInt(request.getParameter("catagories"))));
      post.setPosting(request.getParameter("posting"));
      post.setTags(tag);
      if(user.getRoles().equals(roles)){
          post.setApproved(true);
      } else {
          post.setApproved(false);
      }
      
      
      
      pService.addPost(post);
              
      
      for (String newTag : tagList){
         
            Tags setTag = new Tags(newTag);            
            tService.addTags(setTag);
            tag.add(tService.getTagsById(setTag.getId()));
            tService.createPostTag(post.getPostId(), setTag.getId());
            
           } 
      
      return "redirect:/employee-post";

    }
    
    
    
}
