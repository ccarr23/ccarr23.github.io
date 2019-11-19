/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.daos;

import com.mastery.masteryBlog.dtos.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author corey
 */
public class CategoryDAOTest {
    
    public CategoryDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addCatagory method, of class CategoryDAO.
     */
    @org.junit.Test
    public void testAddCatagory() {
        System.out.println("addCatagory");
        Category catagory = null;
        CategoryDAO instance = new CategoryDAOImpl();
        Category expResult = null;
        Category result = instance.addCatagory(catagory);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCatagoryById method, of class CategoryDAO.
     */
    @org.junit.Test
    public void testGetCatagoryById() {
        System.out.println("getCatagoryById");
        int catagoryId = 0;
        CategoryDAO instance = new CategoryDAOImpl();
        Category expResult = null;
        Category result = instance.getCatagoryById(catagoryId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllCatagories method, of class CategoryDAO.
     */
    @org.junit.Test
    public void testGetAllCatagories() {
        System.out.println("getAllCatagories");
        CategoryDAO instance = new CategoryDAOImpl();
        List<Category> expResult = null;
        List<Category> result = instance.getAllCatagories();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCatagory method, of class CategoryDAO.
     */
    @org.junit.Test
    public void testUpdateCatagory() {
        System.out.println("updateCatagory");
        Category catagoryId = null;
        CategoryDAO instance = new CategoryDAOImpl();
        instance.updateCatagory(catagoryId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCatagory method, of class CategoryDAO.
     */
    @org.junit.Test
    public void testDeleteCatagory() {
        System.out.println("deleteCatagory");
        int catagoryId = 0;
        CategoryDAO instance = new CategoryDAOImpl();
        instance.deleteCatagory(catagoryId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCatagoryByPostId method, of class CategoryDAO.
     */
    @org.junit.Test
    public void testGetCatagoryByPostId() {
        System.out.println("getCatagoryByPostId");
        int postId = 0;
        CategoryDAO instance = new CategoryDAOImpl();
        List<Category> expResult = null;
        List<Category> result = instance.getCatagoryByPostId(postId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Transactional
    public class CategoryDAOImpl implements CategoryDAO {
        
    @Autowired
    JdbcTemplate jdbc;
    
    private final String createCatagory = "Insert Into Catagory(`description`) Values (?)";
    private final String getCatagoryById = "Select * From Catagory Where catagoryId = ?";
    private final String getAllCatagories = "Select * From Catagory";
    private final String updateCatagory = "Update Catagory Set `description` = ? Where catagoryId = ?";
    private final String deleteCatagory = "Delete From Catagory Where catagoryId = ?";
    

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
    
            public final class CatagoryJDBCMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            
            Category catagory = new Category();
            
            catagory.setCatagoryId(rs.getInt("catagoryId"));
            catagory.setDescription(rs.getString("description"));
            
            return catagory;
            
        }
    }


    }
    
}
