package com.example.demo.dao.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseDAO<T, ID extends Serializable> extends JpaRepository<T, ID> {

}