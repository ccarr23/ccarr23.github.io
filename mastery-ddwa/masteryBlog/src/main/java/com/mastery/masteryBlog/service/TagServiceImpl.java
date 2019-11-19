/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.service;

import com.mastery.masteryBlog.daos.TagDAO;
import com.mastery.masteryBlog.dtos.Tags;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    
    @Autowired
    TagDAO tDao;

    @Override
    public Tags addTags(Tags tags) {
        return tDao.addTags(tags);
    }

    @Override
    public Tags getTagsById(int tagId) {
        return tDao.getTagsById(tagId);
    }

    @Override
    public List<Tags> getAllTags() {
        return tDao.getAllTags();
    }

    @Override
    public void updateTag(Tags tag) {
        tDao.updateTag(tag);
    }

    @Override
    public void deleteTag(int tagId) {
        tDao.deleteTag(tagId);
    }

    @Override
    public List<Tags> getTagsByPostId(int postId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createPostTag(int postId, int tagId) {
        tDao.createPostTag(postId, tagId);
    }

    @Override
    public void deletePost(int postId) {
        tDao.deletePost(postId);
    }
    
}
