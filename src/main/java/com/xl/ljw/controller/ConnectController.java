package com.xl.ljw.controller;

import com.xl.ljw.entity.ConnectEntity;
import com.xl.ljw.service.ConnectService;
import com.xl.ljw.service.serviceImpl.ConnectServiceImpl;
import com.xl.ljw.until.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/connect")
public class ConnectController {

    @Autowired
    private ConnectServiceImpl connectServiceImpl;

    @GetMapping("/connectDesc")
    public Object connect(){

       List<ConnectEntity> list = connectServiceImpl.findAll();

       return ResultResponse.resultResponse(200,"请求成功",list);
    }
}
