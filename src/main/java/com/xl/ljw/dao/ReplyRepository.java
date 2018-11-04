package com.xl.ljw.dao;

import com.xl.ljw.entity.ReplyEntity;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Serializable> {

    @Query(value = "select count(*) from article where article_id = ?1",nativeQuery = true)
   int findReplyCount(Integer articleId);

}
