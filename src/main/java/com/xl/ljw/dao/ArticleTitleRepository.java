package com.xl.ljw.dao;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.entity.ArticleTitleEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface ArticleTitleRepository extends JpaRepository<ArticleTitleEntity, Serializable> {

    @Query(value = "select * from article_title where sign = 0 AND del_flag = 0 order by create_time desc LIMIT 4",nativeQuery = true)
    public List<ArticleTitleEntity> findArticleTitleAll();

    public List<ArticleTitleEntity> findByDelFlag(Integer delFlag,Pageable pageable);
    //List<ArticleTitleEntity> findAll(Example<ArticleTitleEntity> example, Sort sort, Pageable pageable);
    @Query(value = "select count(r.article_id) as replyCount FROM reply AS r WHERE r.article_id = ?1 ",nativeQuery = true)
    public int findReplyCount(Integer articleId);

    @Query(value = "select a.browse_count AS browseCount FROM article AS a WHERE a.article_id = ?1 ",nativeQuery = true)
    public int findBrowseCount(Integer articleId);

    @Query(value = "SELECT COUNT( r.article_id ) AS replyCount,a.article_title as articleTitle,a.article_id as articleId FROM article_title AS a,reply AS r WHERE a.article_id = r.article_id  AND a.del_flag = 0 AND r.del_flag = 0 GROUP BY a.article_id ORDER BY COUNT( r.article_id )  DESC LIMIT 8",nativeQuery = true)
    public List<JSONObject> findHostArticleTitle();

    public List<ArticleTitleEntity> findByUserIdAndDelFlag(Integer userId,Integer delFlag,Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE article_title set del_flag = 1 where article_id = ?1",nativeQuery = true)
    public int updateUserArticleTitle(Integer articleId);

    @Transactional
    @Modifying
    @Query(value = "update article_title set photo = ?1,name = ?3 where user_id = ?2",nativeQuery = true)
    public int updatePhotoAndName(String photo,Integer userId,String name);

    public List<ArticleTitleEntity> findByUserIdAndDelFlag(Integer userId,Integer delFlag);

    @Query(value = "select count(*) from article_title where del_flag = 0",nativeQuery = true)
    public int findAllArticleTitleCount();
}
