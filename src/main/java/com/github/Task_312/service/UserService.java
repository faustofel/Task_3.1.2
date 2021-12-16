package com.github.Task_312.service;

import com.github.Task_312.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User getUserById(Long id);
    public User getUserByName(String userName);

    public void addUser(User user);
    public void updateUser(User user);
    public void removeUser(Long id);

}
