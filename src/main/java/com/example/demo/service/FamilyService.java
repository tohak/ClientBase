package com.example.demo.service;

import com.example.demo.dao.FamilyDAO;
import com.example.demo.domain.Family;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class FamilyService {
    private final FamilyDAO familyDAO;

    @Autowired
    public FamilyService(FamilyDAO familyDAO) {
        this.familyDAO = familyDAO;
    }

    public Family getById(Long id) {
        checkArgument(id != null, "Received incoming parameter null!");
        Family entity = familyDAO.findOneById(id);
        if (entity == null) {
            throw new EntityNotFoundException("not Family");
        }
        return entity;
    }

    public List<Family> getAll() {
        return familyDAO.findAll();
    }

    public boolean addNewFamily(User user) {
        Family family= new Family();
        if (user!=null){
            family.setUsers(Collections.singleton(user));
            familyDAO.save(family);
            return true;
        }
        return false;
    }

}
