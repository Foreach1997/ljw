package com.xl.ljw.dao;


import com.xl.ljw.entity.ReplyUserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ReplyUserRepository  extends JpaRepository<ReplyUserEntity, Serializable> {

   public List<ReplyUserEntity> findByReplyIdAndDelFlag(Integer replyId,Integer delFlag,Sort sort);


   @Query("update ReplyUserEntity set photo = ?1,name = ?3 where userId = ?2")
   public int updatePhotoAndName(String photo,Integer userId,String name);
}
