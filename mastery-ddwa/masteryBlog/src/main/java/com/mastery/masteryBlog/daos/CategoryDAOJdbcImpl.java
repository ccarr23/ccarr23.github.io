/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.daos;

import com.mastery.masteryBlog.dtos.Category;
import com.mastery.masteryBlog.dtos.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOJdbcImpl implements CategoryDAO {
    
    @Autowired
    JdbcTemplate jdbc;
    
    private final String createCatagory = "Insert Into Catagory(`description`) Values (?)";
    private final String getCatagoryById = "Select * From Catagory Where catagoryId = ?";
    private final String getAllCatagories = "Select * From Catagory";
    private final String updateCatagory = "Update Catagory Set `description` = ? Where catagoryId = ?";
    private final String deleteCatagory = "Delete From Catagory Where catagoryId = ?";
    
    public CategoryDAOJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public Category addCatagory(Category catagory) {
        jdbc.update(createCatagory, catagory.getDescription());
        catagory.setCatagoryId(jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        return catagory;
    }

    @Override
    public Category getCatagoryById(int catagoryId) {
        return jdbc.queryForObject(getCatagoryById, new CatagoryJDBCMapper(), catagoryId);
    }

    @Override
    public List<Category> getAllCatagories() {
        return jdbc.query(getAllCatagories, new CatagoryJDBCMapper());
    }

    @Override
    public void updateCatagory(Category catagory) {
        jdbc.update(updateCatagory, catagory.getDescription(), catagory.getCatagoryId());
    }

    @Override
    public void deleteCatagory(int catagoryId) {
        jdbc.update(deleteCatagory, catagoryId);
    }

    @Override
    public List<Category> getCatagoryByPostId(int postId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
            public static final class CatagoryJDBCMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            
            Category catagory = new Category();
            
            catagory.setCatagoryId(rs.getInt("catagoryId"));
            catagory.setDescription(rs.getString("description"));
            
            return catagory;
            
        }
    }
    
}
