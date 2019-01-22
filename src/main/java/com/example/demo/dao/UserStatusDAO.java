package com.example.demo.dao;

import com.example.demo.dao.common.BaseDAO;
import com.example.demo.domain.UserStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatusDAO extends BaseDAO<UserStatus, Long> {
    Optional<UserStatus> findById(Long id);

    UserStatus findOneById(Long id);
}
