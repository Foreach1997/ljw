package com.xl.ljw.dao;

import com.xl.ljw.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface UserRepository extends JpaRepository<UserEntity, Serializable> {

  @Query("select phone,name from UserEntity where userId = ?1 and  delFlag = ?2")
  public UserEntity findByUserIdAndDelFlag(Integer userId,Integer delFlag);


}
