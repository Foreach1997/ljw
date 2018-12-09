package com.xl.ljw.service;

import com.xl.ljw.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService {

    /**用户登录*/
    public Object userLogin(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response);
    /**用户注册*/
    public int userReg(UserEntity userEntity);
    /**退出登录*/
    public Object qiut(HttpServletRequest request, HttpServletResponse response);
    /**来访记录*/
    public Object findVisitor();
    /**修改头像*/
    public Object updatePhoto(MultipartFile file,UserEntity userEntity);

}
