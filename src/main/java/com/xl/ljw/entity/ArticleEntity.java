package com.xl.ljw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article")
@Data
public class ArticleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer articleId;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "photo")
    private String photo;


    @Column(name = "article")
    private String article;


    @Column(name = "del_flag")
    private int delFlag;


    @Column(name = "createTime")
    private Date createTime;


    @Column(name = "user_id")
    private Integer userId;







}
