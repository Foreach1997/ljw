package com.xl.ljw.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reply_user")
@Data
public class ReplyUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer replyUserId;

    @Column(name = "reply_id")
    private Integer  replyId;

    @Column(name = "note")
    private String  note;


    @Column(name = "user_id")
    private Integer  userId;

    @Column(name = "name")
    private String  name;

    @Column(name = "article_id")
    private String  articleId;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "sign")
    private String  sign;

    @Column(name = "cover_user_id")
    private String  coverUserId;

    @Column(name = "cover_name")
    private String  coverName;

    @Column(name = "del_flag")
    private int delFlag;

    @Column(name = "photo")
    private  String photo;

    @Transient
    private UserEntity userEntity;

}
