package com.xl.ljw.dao;

import com.xl.ljw.entity.ConnectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ConnectRepository extends JpaRepository<ConnectEntity, Serializable> {

    @Query(value = "select * from connect where del_flag = 0 ORDER BY connect_id desc LIMIT 4",nativeQuery = true)
    public List<ConnectEntity> findAllDesc();


}
