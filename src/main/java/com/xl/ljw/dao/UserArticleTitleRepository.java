package com.xl.ljw.dao;

import com.xl.ljw.entity.UserArticleTitleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface UserArticleTitleRepository extends JpaRepository<UserArticleTitleEntity, Serializable> {

    public List<UserArticleTitleEntity> findByUserIdAndDelFlag(Integer userId, Integer delFlag, Pageable pageable);

    public List<UserArticleTitleEntity> findByUserIdAndDelFlag(Integer userId, Integer delFlag);

   /* @Query(value = "update user_article_title set visit_count = visit_count+1 where user_article_id = ?1",nativeQuery = true)
    public int updateVisitCount(Integer userArticleId);

    @Query(value = "update user_article_title set agree = agree+1 where user_article_id = ?1",nativeQuery = true)
    public int updateAgree(Integer userArticleId);*/

    public UserArticleTitleEntity findByUserArticleIdAndDelFlag(Integer userArticleId,Integer delFlag);

}
