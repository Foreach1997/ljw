package com.xl.ljw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user_article_reply")
public class UserArticleReplyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserArticleReplyId;

    @Column(name = "reply_text")
    private String replyText;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "del_flag")
    private int delFlag;

    @Column(name = "user_article_id")
    private Integer userArticleId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;


}
