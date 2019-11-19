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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author corey
 */
@Controller
public class CategoriesController {
    
    @Autowired
    PostService pService;
    
    @Autowired
    CategoryService cService;
    
    List<Post> staticList;
    
    @GetMapping("categories")
    public String showPage(Model model){
        List<Category> categories = cService.getAllCatagories();
        List<Post> posts = pService.getAllPosts();
        staticList = pService.getAllStaticPost();
        model.addAttribute("Static", staticList);
        model.addAttribute("posts", posts);
        model.addAttribute("categories", categories);
        return "categories";
    }
    
    @PostMapping("addCategory")
    public String addCategory(HttpServletRequest request, Category category) {
        category.setDescription(request.getParameter("description"));
        cService.addCatagory(category);
        
        return "redirect:/categories";
    }
    
    @GetMapping("deleteCategory")
    public String deletePost(HttpServletRequest request) {
       int id = Integer.parseInt(request.getParameter("id"));
       cService.deleteCatagory(id);
       return "redirect:/categories";
    }
    
}
