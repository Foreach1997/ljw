package com.xl.ljw.controller;

import com.xl.ljw.entity.ReplyUserEntity;
import com.xl.ljw.service.serviceImpl.ReplyServiceImpl;
import com.xl.ljw.service.serviceImpl.ReplyUserServiceImpl;
import com.xl.ljw.until.SupportPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replyUser")
public class ReplyUserController {

    @Autowired
    private ReplyUserServiceImpl replyUserServiceImpl;

    @PostMapping("/saveReplyUser")
    public  Object saveReplyUser(ReplyUserEntity replyUserEntity){

     return  replyUserServiceImpl.saveReplyUser(replyUserEntity);

    }

    @GetMapping("/findReplyUser")
    public Object findReplyUser(Integer CoverUserID, SupportPage supportPage){
        return replyUserServiceImpl.findByCoverUserId(CoverUserID, supportPage);
    }


}
