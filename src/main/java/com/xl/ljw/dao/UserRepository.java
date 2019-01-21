package com.xl.ljw.dao;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Serializable> {

  @Query("select phone,name from UserEntity where userId = ?1 and  delFlag = ?2")
  public UserEntity findByUserIdAndDelFlag(Integer userId,Integer delFlag);

  @Transactional
  @Modifying
  @Query(value = "update user set photo = ?1,name = ?3, password = ?4,user_password = ?5 where user_id = ?2",nativeQuery = true)
  public int updatePhotoAndName(String photo,Integer userId,String name,String password,String userPassword);

  public UserEntity getByUserIdAndDelFlag(Integer userId,Integer delFlag);

  @Query(value = "select name,photo,login_count,login_time,user_id from user where del_flag = 0 and login_time is not null order by login_time limit 16",nativeQuery = true)
  public List<JSONObject> findAllVisitor();

  @Query(value = "select userName from UserEntity where userName = ?1")
  public String findByUserName(String userName);
}
