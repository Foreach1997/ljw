package com.xl.ljw.controller;


import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.entity.ArticleTitleEntity;
import com.xl.ljw.service.serviceImpl.ArticleTitleServiceImpl;
import com.xl.ljw.until.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ArticleTitle")
public class ArticleTitleController {

    @Autowired
    private ArticleTitleServiceImpl articleTitleServiceImpl;


     @GetMapping("/ArticleTitles")
     public  Object findArticleTitles(){

         List<JSONObject> articleTitleEntities = articleTitleServiceImpl.findArticleTitles();

        return ResultResponse.resultResponse(200,"请求成功",articleTitleEntities);
     }

}
