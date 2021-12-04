package com.github.Task_311.service;

import com.github.Task_311.DAO.UserDAO;
import com.github.Task_311.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImp(UserDAO userDAO, PasswordEncoder passwordEncoder){
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByName(String userName) {
        return userDAO.getUserByName(userName);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        if(userDAO.getUserById(user.getId()).getPassword().compareTo(user.getPassword())!=0) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.updateUser(user);
    }

    @Override
    public void removeUser(Long id) {
        userDAO.removeUser(id);
    }
}
