package com.xl.ljw.dao;

import com.xl.ljw.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Serializable> {
}
