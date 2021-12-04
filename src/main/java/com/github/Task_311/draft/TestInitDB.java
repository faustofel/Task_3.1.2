package com.github.Task_311.draft;

import com.github.Task_311.model.Role;
import com.github.Task_311.model.User;
import com.github.Task_311.service.RoleService;
import com.github.Task_311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestInitDB {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initialize(){

        roleService.addRole(new Role("ROLE_USER"));
        roleService.addRole(new Role("ROLE_ADMIN"));

        Set<Role> roleSet = new HashSet<>();
        User user;

        roleSet.add(roleService.getRoleByName("ROLE_USER"));

        user = new User("ALICE", "ALICEPASS", "alice@mail.com", "Alice", "AliceLastName");
        user.getRoleSet().addAll(roleSet);
        userService.addUser(user);

        user = new User("BOB", "BOBPASS", "bob@mail.com", "Bob", "BobLastName");
        user.getRoleSet().addAll(roleSet);
        userService.addUser(user);

        user = new User("CARL", "CARLPASS", "carl@mail.com", "Carl", "CarlLastName");
        user.getRoleSet().addAll(roleSet);
        userService.addUser(user);

        roleSet.add(roleService.getRoleByName("ROLE_ADMIN"));

        user = new User("ADMIN", "ADMINPASS", "admin@mail.com", "Admin", "AdminLastName");
        user.getRoleSet().addAll(roleSet);
        userService.addUser(user);
    }

}
