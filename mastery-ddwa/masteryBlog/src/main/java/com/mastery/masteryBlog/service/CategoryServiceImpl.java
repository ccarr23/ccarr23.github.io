/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.service;

import com.mastery.masteryBlog.dtos.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mastery.masteryBlog.daos.CategoryDAO;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    CategoryDAO cDao;

    @Override
    public Category addCatagory(Category catagory) {
        return cDao.addCatagory(catagory);
    }

    @Override
    public Category getCatagoryById(int catagoryId) {
        return cDao.getCatagoryById(catagoryId);
    }

    @Override
    public List<Category> getAllCatagories() {
        return cDao.getAllCatagories();
    }

    @Override
    public void updateCatagory(Category catagoryId) {
        cDao.updateCatagory(catagoryId);
    }

    @Override
    public void deleteCatagory(int catagoryId) {
        cDao.deleteCatagory(catagoryId);
    }

    @Override
    public List<Category> getCatagoryByPostId(int postId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
