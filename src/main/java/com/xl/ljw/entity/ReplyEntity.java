package com.xl.ljw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reply")
@Data
public class ReplyEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer replyId;

    @Column(name = "text")
    private String text;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "del_flag")
    private int delFlag;

    @Transient
    private List<ReplyUserEntity> replyUserEntity;

}
