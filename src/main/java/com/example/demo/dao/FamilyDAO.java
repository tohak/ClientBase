package com.example.demo.dao;

import com.example.demo.dao.common.BaseDAO;
import com.example.demo.domain.Family;
import org.springframework.stereotype.Repository;

/*
 * Базовый ДАО слой сущности семья
 */
@Repository
public interface FamilyDAO extends BaseDAO<Family, Long> {
    Family findOneById(Long id);
}
