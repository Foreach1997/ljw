package com.xl.ljw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "user")
@Data
public class UserEntity implements Serializable {

     private static final long serialVersionUID = 1L;

     @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY)
     private  Integer userId;

     @Column(name = "user_name")
     private  String userName;

     @Column(name = "user_password")
     private  String userPassword;

     @Column(name = "name")
     private  String  name;

     @Column(name = "del_flag")
     private  int  delFlag;

     @Column(name = "email")
     private  String  email;

     @Column(name = "sex")
     private  Integer  sex;


     @Column(name = "login_count")
     private Integer loginCount;

     @Column(name = "phone")
     private  String  phone;

     @Column(name = "photo")
     private  String  photo;

     @Column(name = "password")
     private  String  password;

     @Column(name = "reply")
     private  String  reply;

     @Column(name = "role_id")
     private  Integer  roleId;

   /*  @Column(name = "create_time")
     private Date createTime;*/

     @Column(name = "update_time")
     private Date  updateTime;

}
