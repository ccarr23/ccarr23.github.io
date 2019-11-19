/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.dtos;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author corey
 */
public class Category {
    
    private int catagoryId;
    private String description;
    
    List<Post> posts;

    public int getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(int catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.catagoryId;
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.posts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.catagoryId != other.catagoryId) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.posts, other.posts)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
