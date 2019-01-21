package com.xl.ljw.dao;

import com.xl.ljw.entity.UserArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UserArticleRepository extends JpaRepository<UserArticleEntity, Serializable> {

    public UserArticleEntity findByUserArticleIdAndDelFlag(Integer userArticleId,Integer delFlag);


}
