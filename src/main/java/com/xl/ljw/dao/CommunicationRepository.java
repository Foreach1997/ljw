package com.xl.ljw.dao;

import com.xl.ljw.entity.CommunicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;

public interface CommunicationRepository extends JpaRepository<CommunicationEntity, Serializable> {

    @Transactional
    @Modifying
    @Query(value = "update communication set name = ?2 where user_id = ?1",nativeQuery = true)
    public int updatePhotoAndName(Integer userId,String name);

}
