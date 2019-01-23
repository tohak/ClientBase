package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("login");
        String married = "married";
        String educations = "ELEMENTARY";
        String date = "1991-01-30";


        boolean isUserCreated = userService.addUser(user, married, educations, date);
        Assert.assertTrue(isUserCreated);
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(UserRole.USER)));
        Mockito.verify(userDAO, Mockito.times(1)).save(user);
    }

    @Test
    public void addUserFailTest() {
        User user = new User();
        user.setUsername("login");
        String married = "married";
        String educations = "ELEMENTARY";
        String date = "1991-01-30";

        user.setUsername("John");

        Mockito.doReturn(new User())
                .when(userDAO)
                .findByUsername("John");

        boolean isUserCreated = userService.addUser(user, married, educations, date);
        Assert.assertFalse(isUserCreated);
        Mockito.verify(userDAO, Mockito.times(0)).save(ArgumentMatchers.any(User.class));

    }
}