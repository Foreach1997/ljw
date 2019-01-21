package com.xl.ljw.dao;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.entity.ReplyEntity;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Serializable> {

    @Query(value = "select count(*) from reply where article_id = ?1",nativeQuery = true)
    int findReplyCount(Integer articleId);

    @Transactional
    @Modifying
    @Query(value = "update reply set photo = ?1,name = ?3 where user_id = ?2",nativeQuery = true)
    public int updatePhotoAndName(String photo,Integer userId,String name);

    @Query(value = "SELECT r.reply_id AS replyId, r.text AS text, r.user_id AS userId, r.article_id AS articleId, r.create_time AS createTime, a.article_title AS articleTitle FROM reply AS r, article_title AS a WHERE r.article_id = a.article_id AND r.user_id = ?1 And r.del_flag = 0 AND a.del_flag = 0",nativeQuery = true)
    public List<JSONObject> findByUserIdAndDelFlag(Integer userId);

}
