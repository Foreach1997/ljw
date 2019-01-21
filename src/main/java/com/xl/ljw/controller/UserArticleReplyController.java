package com.xl.ljw.controller;

import com.xl.ljw.dao.UserArticleReplyRepository;
import com.xl.ljw.entity.UserArticleReplyEntity;
import com.xl.ljw.service.serviceImpl.UserArticleReplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userArticleReply")
public class UserArticleReplyController {

    @Autowired
    private UserArticleReplyServiceImpl userArticleReplyServiceImpl;


    @GetMapping("/findUserArticleReply")
    public Object findUserArticleReply(Integer userArticleId){

       return userArticleReplyServiceImpl.findUserArticleReply(userArticleId);

    }


    @PostMapping("/saveUserArticleReply")
    public Object saveUserArticleReply(UserArticleReplyEntity userArticleReplyEntity)
    {
      return     userArticleReplyServiceImpl.saveUserArticleReply(userArticleReplyEntity);
    }




}
