package com.github.Task_311.security;

import com.github.Task_311.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    private UserDAO userDAO;

    @Autowired
    public UserDetailServiceImp(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.getUserByName(s);
    }
}
