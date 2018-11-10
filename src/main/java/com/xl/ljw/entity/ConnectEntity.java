package com.xl.ljw.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "connect")
@Data
public class ConnectEntity {

    @Id
    private int connectId;

    @Column(name = "connect")
    private String connect;

    @Column(name = "note")
    private String note;

    @Column(name = "del_flag")
    private int delFlag;



}
