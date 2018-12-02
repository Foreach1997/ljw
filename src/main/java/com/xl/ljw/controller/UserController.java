package com.xl.ljw.controller;


import com.xl.ljw.entity.UserEntity;
import com.xl.ljw.filter.SessionUtil;
import com.xl.ljw.service.serviceImpl.UserServiceImpl;
import com.xl.ljw.until.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private SessionUtil sessionUtil;

    @PostMapping("/login")
    public Object Login(UserEntity userEntity, HttpServletRequest request,HttpServletResponse response){
     return  userServiceImpl.userLogin(userEntity,request,response);
    }

    @PostMapping("/reg")
    public Object reg(UserEntity userEntity){
        int val =  userServiceImpl.userReg(userEntity);
        if (val>0){
        return ResultResponse.resultResponse(200,"注册成功",null);
        }
        return ResultResponse.resultResponse(250,"注册失败",null);
    }

    @GetMapping("/quit")
    public Object quit(HttpServletRequest request,HttpServletResponse response){
        return  userServiceImpl.qiut(request,response);
    }




    @GetMapping("/checkLogin")
    public Object checkLogin(UserEntity userEntity,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
        System.out.println("checkLogin   "+request.getSession().getAttribute("userId"));
        Object userId = request.getSession().getAttribute("userId");
        //System.out.println(userId);
        if (userId==null){
            return ResultResponse.resultResponse(200,"检查是否登录",null);
        }
       return ResultResponse.resultResponse(250,"登录",null);
    }


}
