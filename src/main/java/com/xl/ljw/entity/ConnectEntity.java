package com.xl.ljw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "connect")
@Data
public class ConnectEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int connectId;

    @Column(name = "connect")
    private String connect;

    @Column(name = "note")
    private String note;

    @Column(name = "del_flag")
    private int delFlag;



}
