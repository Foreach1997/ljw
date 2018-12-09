package com.xl.ljw.dao;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.entity.ArticleTitleEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ArticleTitleRepository extends JpaRepository<ArticleTitleEntity, Serializable> {

    @Query(value = "select * from article_title where sign = 0 order by create_time desc LIMIT 4",nativeQuery = true)
    public List<ArticleTitleEntity> findArticleTitleAll();

    //List<ArticleTitleEntity> findAll(Example<ArticleTitleEntity> example, Sort sort, Pageable pageable);
    @Query(value = "select a.browse_count as browseCount,count(r.article_id) as replyCount FROM reply AS r,article AS a WHERE a.article_id = ?1 AND  r.article_id = a.article_id",nativeQuery = true)
    public JSONObject findBrowseAndreplyCount(Integer articleId);

    @Query(value = "SELECT COUNT( r.article_id ) AS replyCount,a.article_title as articleTitle,a.article_id as articleId FROM article_title AS a,reply AS r WHERE a.article_id = r.article_id  AND a.del_flag = 0 AND r.del_flag = 0 GROUP BY a.article_id ORDER BY COUNT( r.article_id )  DESC LIMIT 8",nativeQuery = true)
    public List<JSONObject> findHostArticleTitle();

    public List<ArticleTitleEntity> findByUserIdAndDelFlag(Integer userId,Integer delFlag,Pageable pageable);

    @Query(value = "UPDATE article_title set del_flag = 1 where article_id = ?1",nativeQuery = true)
    public int updateUserArticleTitle(Integer articleId);

    @Query("update ArticleTitleEntity set photo = ?1,name = ?3 where userId = ?2")
    public int updatePhotoAndName(String photo,Integer userId,String name);

}
