package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.domain.Education;
import com.example.demo.domain.Family;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import com.example.demo.utils.DateUtil;
import com.example.demo.utils.NumbersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/*
 * Сервисный класс пользовательский с логикой: добавить пользователя, удалить, обновить, удалить из семьи,
  * достать пользователя по фильтру, по логину
 */
@Service
public class UserService implements UserDetailsService {
    private final UserDAO userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserStatusService statusService;
    private final FamilyService familyService;

    @Autowired
    public UserService(UserDAO userDao, PasswordEncoder passwordEncoder, UserStatusService statusService, FamilyService familyService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.statusService = statusService;
        this.familyService = familyService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user, String married, String educations, String date) {
        User userFromBD = userDao.findByUsername(user.getUsername());
        if (userFromBD != null || user.getUsername().isEmpty()) {
            return false;
        }
        if ("married".equals(married)) {
            user.setFamilyStatus(true);
        } else {
            user.setFamilyStatus(false);
        }
        Education[] enumArrays = Education.values();
        for (Education ed : enumArrays) {
            if (ed.toString().equals(educations)) {
                user.setState(ed);
            }
        }
        user.setDate(DateUtil.parseLocalDate(date));
        user.setRoles(Collections.singleton(UserRole.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    public List<User> getAll() {
        return userDao.findAll();
    }

    public List<User> getUsers(String filter) {
        if (filter != null && !filter.isEmpty()) {
            List<User> users = getAll();
            Pattern pattern = Pattern.compile(filter);
            List<User> result = getFilterName(pattern, users);
            result.addAll(getFilterLastName(pattern, users));
            result.addAll(getFilterPatronymic(pattern, users));
            return result;
        } else {
            return getAll();
        }
    }

    private List<User> getFilterName(Pattern pattern, List<User> users) {
        return users
                .stream()
                .parallel()
                .filter(user -> pattern.matcher(user.getName()).find())
                .collect(Collectors.toList());
    }

    private List<User> getFilterLastName(Pattern pattern, List<User> users) {
        return users
                .stream()
                .parallel()
                .filter(user -> pattern.matcher(user.getUserLastName()).find())
                .collect(Collectors.toList());
    }

    private List<User> getFilterPatronymic(Pattern pattern, List<User> users) {
        return users
                .stream()
                .parallel()
                .filter(user -> pattern.matcher(user.getPatronymic()).find())
                .collect(Collectors.toList());
    }

    public void saveUser(User user, String login, String userStatus, String family, Map<String, String> form) {
        user.setUsername(login);
        if (!userStatus.isEmpty() && NumbersUtils.isDigit(userStatus)) {
            if (Long.valueOf(userStatus) < 1) {
                user.setUserStatus(null);
            } else {
                user.setUserStatus(statusService.getById(Long.valueOf(userStatus)));
            }
        }
        if (!family.isEmpty() && NumbersUtils.isDigit(family)) {
            user.setFamily(familyService.getById(Long.valueOf(family)));
        }
        Set<String> roles = Arrays.stream(UserRole.values())
                .map(UserRole::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(UserRole.valueOf(key));
            }
        }
        userDao.save(user);
    }

    public User getUserByLogin(String login) {
        return userDao.findByUsername(login);
    }

    public void deleteUserOnFamily(Family family, int[] toDelete) {
        for (int i : toDelete) {
            User user = userDao.findOneById((long) i);
            if (user != null) {
                user.setFamily(null);
                userDao.save(user);
            }
        }
        familyService.saveFamily(family);
    }

    public boolean addUserFromFamily(Family family, String login) {
        User user = getUserByLogin(login);
        if (user != null) {
            user.setFamily(family);
            userDao.save(user);
            return true;
        }
        return false;
    }

    public void deleteFamily(Family family) {
        for (User user : family.getUsers()) {
            user.setFamily(null);
        }
        familyService.delete(family);
    }
}
