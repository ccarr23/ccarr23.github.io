/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.daos;

import com.mastery.masteryBlog.dtos.Category;
import java.util.List;

/**
 *
 * @author corey
 */
public interface CategoryDAO {
    
    Category addCatagory(Category catagory);
    Category getCatagoryById(int catagoryId);
    List<Category> getAllCatagories();
    void updateCatagory(Category catagoryId);
    void deleteCatagory(int catagoryId);
    List<Category> getCatagoryByPostId(int postId);
    
    
}
