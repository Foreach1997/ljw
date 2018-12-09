package com.xl.ljw.dao;

import com.xl.ljw.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Serializable> {

    @Query(value = "UPDATE article set del_flag = 1 where article_id = ?1",nativeQuery = true)
    public int updateUserArticleTitle(Integer articleId);

    @Query("update ArticleEntity set photo = ?1 where userId = ?2")
    public int updatePhotoAndName(String photo,Integer userId);
}
