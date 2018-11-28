package com.xl.ljw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reply")
@Data
public class ReplyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int replyId;

    @Column(name = "text")
    private int text;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private int name;

    @Column(name = "photo")
    private int photo;

    @Column(name = "article_id")
    private int articleId;

    @Column(name = "update_time")
    private int updateTime;

    @Column(name = "create_time")
    private int createTime;

    @Column(name = "del_flag")
    private int delFlag;

}
