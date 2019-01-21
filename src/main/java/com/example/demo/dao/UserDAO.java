package com.example.demo.dao;

import com.example.demo.dao.common.BaseDAO;
import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends BaseDAO<User, Long> {
    User findByLogin(String login);
}
