package com.xl.ljw.dao;

import com.xl.ljw.entity.ArticleTitleEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ArticleTitleRepository extends JpaRepository<ArticleTitleEntity, Serializable> {

    @Query(value = "select * from article_title order by create_time desc LIMIT 4",nativeQuery = true)
    public List<ArticleTitleEntity> findArticleTitleAll();

    //List<ArticleTitleEntity> findAll(Example<ArticleTitleEntity> example, Sort sort, Pageable pageable);

}
