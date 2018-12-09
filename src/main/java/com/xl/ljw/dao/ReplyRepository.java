package com.xl.ljw.dao;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.entity.ReplyEntity;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Serializable> {

    @Query(value = "select count(*) from reply where article_id = ?1",nativeQuery = true)
    int findReplyCount(Integer articleId);

    @Query("update ReplyEntity set photo = ?1,name = ?3 where userId = ?2")
    public int updatePhotoAndName(String photo,Integer userId,String name);

}
