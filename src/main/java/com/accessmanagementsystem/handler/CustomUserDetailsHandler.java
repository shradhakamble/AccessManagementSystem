package com.accessmanagementsystem.handler;

import com.accessmanagementsystem.repository.UserRepository;
import com.accessmanagementsystem.repository.entity.DBUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsHandler implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DBUsers dbUsers = repository.findByUserName(username);
        return new User(dbUsers.getUserName(), dbUsers.getPassword(), new ArrayList<>());
    }
}
