package com.xl.ljw.dao;


import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.entity.ReplyUserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface ReplyUserRepository  extends JpaRepository<ReplyUserEntity, Serializable> {

   public List<ReplyUserEntity> findByReplyIdAndDelFlag(Integer replyId,Integer delFlag,Sort sort);

   @Transactional
   @Modifying
   @Query(value = "update reply_user set photo = ?1,name = ?3 where user_id = ?2",nativeQuery = true)
   public int updatePhotoAndName(String photo,Integer userId,String name);

   public List<ReplyUserEntity> findByCoverUserIdAndDelFlag(Integer CoverUserID,Integer DelFlag);

   @Query(value = "SELECT r.reply_id AS replyId, r.note AS note,r.name AS name,r.user_id AS userId, r.article_id AS articleId, r.create_time AS createTime, a.article_title AS articleTitle FROM reply_user AS r, article_title AS a WHERE r.article_id = a.article_id AND r.cover_user_id = ?1 And r.del_flag = 0 AND a.del_flag = 0",nativeQuery = true)
   public List<JSONObject> findByCoverUserIdAndDelFlag(Integer userId);
}
