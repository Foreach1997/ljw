package com.xl.ljw.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "article_title")
@Data
public class ArticleTitleEntity implements Serializable {

    /**文章标题id*/
    @Id
    private  Integer articleTitleId;

    @Column(name = "article_id")
    private  Integer articleId;

    @Column(name = "article_title")
    private  String articleTitle;

    @Column(name = "article_type")
    private  String articleType;

    @Column(name = "user_id")
    private  Integer userId;

    @Column(name = "name")
    private  String name;

    @Column(name = "del_flag")
    private  Integer delFlag;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "photo")
    private  String photo;


    @Transient
    private Integer replyCount;
}
