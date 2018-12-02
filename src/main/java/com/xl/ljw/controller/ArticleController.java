package com.xl.ljw.controller;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.entity.ArticleEntity;
import com.xl.ljw.entity.ArticleTitleEntity;
import com.xl.ljw.entity.UserEntity;
import com.xl.ljw.service.ArticleService;
import com.xl.ljw.service.serviceImpl.ArticleServiceImpl;
import com.xl.ljw.until.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleServiceImpl;

    @PostMapping("/report")
    public Object report(ArticleEntity articleEntity,ArticleTitleEntity articleTitleEntity){

    return articleServiceImpl.report(articleEntity,articleTitleEntity);

    }

    @GetMapping("/articleText")
    public Object articleText(ArticleEntity articleEntity){
     return  articleServiceImpl.findArticle(articleEntity);
    }

    @PostMapping("/uploadPhoto")
    public Object uploadPhoto(MultipartFile file){
     return   articleServiceImpl.uploadPhoto(file);
    }


}
