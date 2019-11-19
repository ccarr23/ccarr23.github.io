/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.controllers;

import com.mastery.masteryBlog.dtos.Post;
import com.mastery.masteryBlog.service.CategoryService;
import com.mastery.masteryBlog.service.PostService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author corey
 */
@Controller
public class ShowCategoriesController {
  
    @Autowired
    PostService pService;
    
    @Autowired
    CategoryService cService;
    
    List<Post> staticList;
    
    @GetMapping("show-category")
    public String showPage(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        List<Post> posts = pService.getPostsByCatagoryId(id);
        staticList = pService.getAllStaticPost();
        model.addAttribute("Static", staticList);
        model.addAttribute("Posts", posts);
        return "show-category";
    }
}
