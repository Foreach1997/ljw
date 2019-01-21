package com.xl.ljw.dao;

import com.xl.ljw.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Serializable> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE article set del_flag = 1 where article_id = ?1",nativeQuery = true)
    public int updateUserArticleTitle(Integer articleId);

    @Transactional
    @Modifying
    @Query(value = "update article set photo = ?1 where user_id = ?2",nativeQuery = true)
    public int updatePhotoAndName(String photo,Integer userId);
}
