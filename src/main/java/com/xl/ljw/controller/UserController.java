package com.xl.ljw.controller;


import com.xl.ljw.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public Object Login(String userName, String userPassword ){

     return  userServiceImpl.userLogin(userName,userPassword);

    }

}
