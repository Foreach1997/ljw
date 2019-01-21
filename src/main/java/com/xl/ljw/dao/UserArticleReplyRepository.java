package com.xl.ljw.dao;

import com.xl.ljw.entity.UserArticleReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface UserArticleReplyRepository extends JpaRepository<UserArticleReplyEntity, Serializable> {

     public List<UserArticleReplyEntity> findByUserArticleIdAndDelFlag(Integer userArticleId,Integer delFlag);

     @Transactional
     @Modifying
     @Query(value = "update user_article_reply set photo = ?1,name = ?3 where user_id = ?2",nativeQuery = true)
     public int updatePhotoAndName(String photo,Integer userId,String name);
}
