package com.example.demo.dao;

import com.example.demo.dao.common.BaseDAO;
import com.example.demo.domain.UserStatus;
import org.springframework.stereotype.Repository;

/*
 * Базовый ДАО слой сущности статус пользователя
 */
@Repository
public interface UserStatusDAO extends BaseDAO<UserStatus, Long> {
    UserStatus findOneById(Long id);
}
