package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private final UserDAO userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = userDao.findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user, String married,String educations, String date) {
        User userFromBD = userDao.findByLogin(user.getLogin());

        if (userFromBD != null || user.getLogin().isEmpty()) {
            return false;
        }
        user.setRoles(Collections.singleton(UserRole.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }
}
