package com.xl.ljw.controller;


import com.xl.ljw.entity.ReplyEntity;
import com.xl.ljw.service.serviceImpl.ReplyServiceImpl;
import com.xl.ljw.until.SupportPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyServiceImpl replyServiceImpl;

    @GetMapping("/findReply")
    public Object findReply(ReplyEntity replyEntity, SupportPage supportPage){

        return replyServiceImpl.findReply(replyEntity,supportPage);

    }

    @PostMapping("/saveReply")
    public Object saveReply(ReplyEntity replyEntity){

        return replyServiceImpl.saveReply(replyEntity);
    }

}
