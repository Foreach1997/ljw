package com.xl.ljw.dao;

import com.xl.ljw.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UserRepository extends JpaRepository<UserEntity, Serializable> {




}
