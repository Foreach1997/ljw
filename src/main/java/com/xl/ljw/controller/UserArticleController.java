package com.xl.ljw.controller;


import com.xl.ljw.entity.UserArticleEntity;
import com.xl.ljw.entity.UserArticleTitleEntity;
import com.xl.ljw.service.serviceImpl.UserArticleServiceImpl;
import com.xl.ljw.service.serviceImpl.UserArticleTitleServiceImpl;
import com.xl.ljw.until.SupportPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/userArticle")
public class UserArticleController {


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private UserArticleServiceImpl userArticleServiceImpl;

    @Autowired
    private UserArticleTitleServiceImpl userArticleTitleServiceImpl;

    @GetMapping("/findAllUserArticleTitle")
    public Object findAllUserArticleTitle(Integer userId, SupportPage supportPage){

        return   userArticleTitleServiceImpl.findAllUserArticle(userId,supportPage);
    }

    @PostMapping("/saveUserArticle")
    public Object saveUserArticle(UserArticleEntity userArticleEntity, UserArticleTitleEntity userArticleTitleEntity){

     userArticleEntity.setCreateTime(new Date());

     userArticleEntity =  userArticleServiceImpl.saveUserArticle(userArticleEntity);

     userArticleTitleEntity.setUserArticleId(userArticleEntity.getUserArticleId());

     userArticleTitleEntity.setCreateTime(new Date());
     userArticleTitleEntity.setAgree(0);
     userArticleTitleEntity.setVisitCount(0);
     return userArticleTitleServiceImpl.saveUserArticle(userArticleTitleEntity);
    }


    @GetMapping("/updateVisitCount")
    public Object updateVisitCount(Integer userArticleId){

     return  userArticleTitleServiceImpl.updateVisitCount(userArticleId);

    }

    @GetMapping("/updateAgree")
    public Object updateAgree(Integer userArticleTitleId)
    {
      return   userArticleTitleServiceImpl.updateAgree(userArticleTitleId);
    }

    @GetMapping("/findUserArticle")
    public Object findUserArticle(Integer userArticleId){
        return userArticleServiceImpl.findUserArticle(userArticleId);
    }



}
