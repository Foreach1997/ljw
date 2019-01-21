package com.xl.ljw.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user_article")
public class UserArticleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userArticleId;

    @Column(name = "user_article_text")
    private String userArticleText;

    @Column(name = "del_flag")
    private int delFlag;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "user_article_title_id")
    private Integer userArticleTitleId;

    @Column(name = "user_id")
    private Integer userId;



}
