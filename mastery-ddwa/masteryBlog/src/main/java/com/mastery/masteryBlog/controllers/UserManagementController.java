/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.controllers;

import com.mastery.masteryBlog.dtos.Post;
import com.mastery.masteryBlog.dtos.Role;
import com.mastery.masteryBlog.dtos.User;
import com.mastery.masteryBlog.service.PostService;
import com.mastery.masteryBlog.service.RoleService;
import com.mastery.masteryBlog.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author corey
 */
@Controller
public class UserManagementController {
    
    @Autowired
    UserService uService;
    
    @Autowired
    RoleService rService;
    
    @Autowired
    PostService pService;
    
    @Autowired
    PasswordEncoder encoder;
    
    List<Post> staticList;
    
    
    @GetMapping("/user-management")
    public String displayAdminPage(Model model) {
        model.addAttribute("users", uService.getAllUsers());
        staticList = pService.getAllStaticPost();
        model.addAttribute("Static", staticList);
        return "user-management";
    }
    
    @PostMapping("/addUser")
    public String addUser(String username, String password, User user) {
        
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEnabled(true);
        
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(rService.getRoleByRoleName("ROLE_USER"));
        user.setRoles(userRoles);
        
        uService.createUser(user);
        
        return "redirect:/user-management";
    }
    
    @PostMapping("/deleteUser")
    public String deleteUser(Integer id) {
        uService.deleteUser(id);
        return "redirect:/user-management";
    }
    
    @PostMapping("editUser")
    public String editUserAction(String[] roleIdList, Boolean enabled, Integer id) {
        User user = uService.getUserById(id);
        if(enabled != null) {
            user.setEnabled(enabled);
        } else {
            user.setEnabled(false);
        }
        
        Set<Role> roleList = new HashSet<>();
        for(String roleId : roleIdList) {
            Role role = rService.getRoleById(Integer.parseInt(roleId));
            roleList.add(role);
        }
        user.setRoles(roleList);
        uService.updateUser(user);
        
        return "redirect:/user-management";
    }
    
    @PostMapping("editPassword") 
    public String editPassword(Integer id, String password, String confirmPassword) {
        User user = uService.getUserById(id);
        
        if(password.equals(confirmPassword)) {
            user.setPassword(encoder.encode(password));
            uService.updateUser(user);
            return "redirect:/user-management";
        } else {
            return "redirect:/editUser?id=" + id + "&error=1";
        }
    }
    
        @GetMapping("/editUser")
    public String editUserDisplay(Model model, Integer id, Integer error) {
        User user = uService.getUserById(id);
        List roleList = rService.getAllRoles();
        
        model.addAttribute("user", user);
        model.addAttribute("roles", roleList);
        
        if(error != null) {
            if(error == 1) {
                model.addAttribute("error", "Passwords did not match, password was not updated.");
            }
        }
        
        return "editUser";
    }
    
}
