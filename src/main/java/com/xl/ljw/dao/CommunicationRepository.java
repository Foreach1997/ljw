package com.xl.ljw.dao;

import com.xl.ljw.entity.CommunicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface CommunicationRepository extends JpaRepository<CommunicationEntity, Serializable> {

    @Query("update CommunicationEntity set name = ?2 where userId = ?1")
    public int updatePhotoAndName(Integer userId,String name);

}
