package com.github.Task_312.DAO;

import com.github.Task_312.model.Role;

import java.util.List;

public interface RoleDAO {

    public List<Role> getAllRoles();
    public Role getRoleById(Long id);
    public Role getRoleByName(String roleName);

    public void addRole(Role role);
    public void updateRole(Role role);
    public void removeRole(Long id);

}
