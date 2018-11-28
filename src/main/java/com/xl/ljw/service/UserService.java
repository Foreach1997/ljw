package com.xl.ljw.service;

import com.xl.ljw.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService {

    public Object userLogin(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response);

    public int userReg(UserEntity userEntity);

    public Object qiut(HttpServletRequest request, HttpServletResponse response);

}
