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
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.mastery.masteryBlog.service.CategoryService;
import com.mastery.masteryBlog.service.RoleService;
import com.mastery.masteryBlog.service.UserService;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author corey
 */

@Controller
public class AdminPostController {
    
    @Autowired
    UserDetailsServiceImpl service;
    
    @Autowired
    UserService uService;
    
    @Autowired
    RoleService rService;
    
    @Autowired
    CategoryService cService;
    
    @Autowired
    PostService pService;
    
    @Autowired
    TagService tService;
    
    List<Post> staticList;
    
 
    
    
    @GetMapping("admin-post")
    public String showPage(Model model) {   
        List<Category> Categories = cService.getAllCatagories(); 
        staticList = pService.getAllStaticPost();
        model.addAttribute("Static", staticList);       
        model.addAttribute("Categories", Categories);
        return "admin-post";
    }
    
    @GetMapping("static")
    public String showStatic(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Post post = pService.getPostById(id);
        staticList = pService.getAllStaticPost();
        model.addAttribute("Static", staticList);
        model.addAttribute("post", post);
    return "static";
    }
    
    
    @PostMapping("addPost")
    public String addPost(HttpServletRequest request, Post post, Principal principal, Boolean isStatic){
        
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
      
      if(isStatic != null){
          post.setIsStatic(isStatic);
      } else {
          post.setIsStatic(false);
      }
        
      pService.addPost(post);
      
      if(post.isIsStatic() == true){
          staticList.add(pService.getPostById(post.getPostId()));
      }
              
      
      for (String newTag : tagList){
         
            Tags setTag = new Tags(newTag);            
            tService.addTags(setTag);
            tag.add(tService.getTagsById(setTag.getId()));
            tService.createPostTag(post.getPostId(), setTag.getId());
            
           } 
      
      return "redirect:/admin-post";

    }
    
}
