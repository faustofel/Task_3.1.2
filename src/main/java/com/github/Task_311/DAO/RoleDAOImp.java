package com.github.Task_311.DAO;

import com.github.Task_311.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleDAOImp implements RoleDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role", Role.class)
                            .getResultList();
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.createQuery("FROM Role WHERE roleName=:roleName",Role.class)
                            .setParameter("roleName", roleName)
                            .getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void removeRole(Long id) {
        entityManager.remove(getRoleById(id));
    }
}
