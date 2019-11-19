/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.service;

import com.mastery.masteryBlog.daos.RoleDAO;
import com.mastery.masteryBlog.dtos.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    RoleDAO rDao;

    @Override
    public Role createRole(Role role) {
        return rDao.createRole(role);
    }

    @Override
    public Role getRoleById(int role) {
        try {
            return rDao.getRoleById(role);
        } catch (DataAccessException ex) {
          return null;  
        }
    }

    @Override
    public Role getRoleByRoleName(String role) {
        try {
            return rDao.getRoleByRoleName(role);
        } catch (DataAccessException ex) {
          return null;  
        }
    }

    @Override
    public List<Role> getAllRoles() {
       return rDao.getAllRoles();
    }

    @Override
    public void deleteRole(int id) {
        rDao.deleteRole(id);
    }

    @Override
    public void updateRole(Role role) {
        rDao.updateRole(role);
    }
    
}
