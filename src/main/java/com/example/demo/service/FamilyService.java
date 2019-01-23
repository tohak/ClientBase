package com.example.demo.service;

import com.example.demo.dao.FamilyDAO;
import com.example.demo.domain.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/*
 * Сервисный класс с логикой: достать из бд, обновить, удалить семью
 */
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

    public synchronized Family saveFamily(Family family) {
        familyDAO.save(family);
        return family;
    }

    public synchronized void delete(Family family) {
        family.setUsers(null);
        familyDAO.delete(family);
    }
}
