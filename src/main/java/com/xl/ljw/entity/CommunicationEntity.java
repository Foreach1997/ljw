package com.xl.ljw.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "communication")
@Entity
public class CommunicationEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer communicationId;

    private Integer userId;

    private String name;

    private String text;

    private int delFlag;

    private Date createTime;


}
