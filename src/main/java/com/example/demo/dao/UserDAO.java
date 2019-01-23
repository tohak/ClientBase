package com.example.demo.dao;

import com.example.demo.dao.common.BaseDAO;
import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Базовый ДАО слой сущности пользователя
 */
@Repository
public interface UserDAO extends BaseDAO<User, Long> {
    User findByUsername(String username);

    User findOneById(Long id);

    List<User> findAllByFamilyOrderById(Long id);
}
