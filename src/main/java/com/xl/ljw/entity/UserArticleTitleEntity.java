package com.xl.ljw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user_article_title")
public class UserArticleTitleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userArticleTitleId;

    @Column(name = "user_article_title")
    private String userArticleTitle;

    @Column(name = "user_article_note")
    private String userArticleNote;

    @Column(name = "del_flag")
    private int delFlag;

    @Column(name = "visit_count")
    private Integer visitCount;

    @Column(name = "agree")
    private Integer agree;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_article_id")
    private Integer userArticleId;

}
