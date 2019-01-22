package com.example.demo.service;

import com.example.demo.dao.UserStatusDAO;
import com.example.demo.domain.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class UserStatusService {
    private final UserStatusDAO userStatusDAO;

    @Autowired
    public UserStatusService(UserStatusDAO userStatusDAO) {
        this.userStatusDAO = userStatusDAO;
    }

    public UserStatus getById(Long id) {
        checkArgument(id != null, "Received incoming parameter null!");
        UserStatus entity = userStatusDAO.findOneById(id);
        if (entity == null) {
            throw new EntityNotFoundException("not UserStatus");
        }
        return entity;
    }
}
