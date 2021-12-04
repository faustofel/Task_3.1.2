package com.github.Task_311.service;

import com.github.Task_311.model.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();
    public Role getRoleById(Long id);
    public Role getRoleByName(String roleName);

    public void addRole(Role role);
    public void updateRole(Role role);
    public void removeRole(Long id);

}
