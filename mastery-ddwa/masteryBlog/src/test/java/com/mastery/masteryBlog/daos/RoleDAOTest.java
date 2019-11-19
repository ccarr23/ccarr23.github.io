/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.daos;

import com.mastery.masteryBlog.dtos.Role;
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
public class RoleDAOTest {
    
    public RoleDAOTest() {
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
     * Test of createRole method, of class RoleDAO.
     */
    @Test
    public void testCreateRole() {
        System.out.println("createRole");
        Role role = null;
        RoleDAO instance = new RoleDAOImpl();
        Role expResult = null;
        Role result = instance.createRole(role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoleById method, of class RoleDAO.
     */
    @Test
    public void testGetRoleById() {
        System.out.println("getRoleById");
        int role = 0;
        RoleDAO instance = new RoleDAOImpl();
        Role expResult = null;
        Role result = instance.getRoleById(role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoleByRoleName method, of class RoleDAO.
     */
    @Test
    public void testGetRoleByRoleName() {
        System.out.println("getRoleByRoleName");
        String role = "";
        RoleDAO instance = new RoleDAOImpl();
        Role expResult = null;
        Role result = instance.getRoleByRoleName(role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllRoles method, of class RoleDAO.
     */
    @Test
    public void testGetAllRoles() {
        System.out.println("getAllRoles");
        RoleDAO instance = new RoleDAOImpl();
        List<Role> expResult = null;
        List<Role> result = instance.getAllRoles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRole method, of class RoleDAO.
     */
    @Test
    public void testDeleteRole() {
        System.out.println("deleteRole");
        int id = 0;
        RoleDAO instance = new RoleDAOImpl();
        instance.deleteRole(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRole method, of class RoleDAO.
     */
    @Test
    public void testUpdateRole() {
        System.out.println("updateRole");
        Role role = null;
        RoleDAO instance = new RoleDAOImpl();
        instance.updateRole(role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Transactional
    public class RoleDAOImpl implements RoleDAO {

    @Autowired
    JdbcTemplate jdbc;
    
    private final String createRole = "Insert Into role(role) Values(?)";
    private final String getRoleById = "Select * From role Where id = ?";
    private final String getAllRoles = "Select * From role";
    private final String getRoleByName = "Select * From role Where role = ?";
    private final String updateRole = "Update role Set role = ? Where id = ?";
    private final String deleteRole = "Delete From role Where id = ?";
    private final String deleteRoleIdFromUserRole = "Delete From user_role Where role_id = ?";
    

    @Override
    public Role createRole(Role role) {
        jdbc.update(createRole, role.getRole());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        role.setId(newId);
        return role;
    }

    @Override
    public Role getRoleById(int id) {
        return jdbc.queryForObject(getRoleById, new RoleMapper(), id);
    }

    @Override
    public Role getRoleByRoleName(String role) {
        return jdbc.queryForObject(getRoleByName, new RoleMapper(), role);
    }

    @Override
    public List<Role> getAllRoles() {
        return jdbc.query(getAllRoles, new RoleMapper());
    }

    @Override
    public void deleteRole(int id) {
        jdbc.update(deleteRoleIdFromUserRole);
        jdbc.update(deleteRole, id);
    }

    @Override
    public void updateRole(Role role) {
        jdbc.update(updateRole, role.getRole(), role.getId());
    }
    
    public final class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int i) throws SQLException {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setRole(rs.getString("role"));
            return role;
        }
    }
    }
    
}
