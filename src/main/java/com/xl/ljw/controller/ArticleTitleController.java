package com.xl.ljw.controller;


import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.entity.ArticleTitleEntity;
import com.xl.ljw.service.serviceImpl.ArticleTitleServiceImpl;
import com.xl.ljw.until.ResultResponse;
import com.xl.ljw.until.SupportPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/ArticleTitle")
public class ArticleTitleController {

    @Autowired
    private ArticleTitleServiceImpl articleTitleServiceImpl;


     @GetMapping("/ArticleTitles")
     public  Object findArticleTitles(HttpServletResponse response, HttpServletRequest request){

         List<JSONObject> articleTitleEntities = articleTitleServiceImpl.findArticleTitles();

         System.out.println(request.getSession().getAttribute("name"));

        return ResultResponse.resultResponse(200,"请求成功",articleTitleEntities);
     }

    @GetMapping("/PageArticleTitles")
    public  Object PageArticleTitles(ArticleTitleEntity articleTitleEntity, SupportPage supportPage){

        return  articleTitleServiceImpl.findAllArticleTitles(articleTitleEntity,supportPage);

    }

    @GetMapping("/findHostArticleTitle")
    public  Object findHostArticleTitle(){
         return articleTitleServiceImpl.findHostArticleTitle();
    }

    @GetMapping("/userArticleTitle")
    public  Object findUserArticleTitle(Integer userId,SupportPage supportPage){
         return  articleTitleServiceImpl.findUserArticleTitle(userId,supportPage);

    }

    @PostMapping("/delUserArticleTitle")
    public Object delUserArticle(Integer articleId){

        return  articleTitleServiceImpl.delUserArticleTitle(articleId);
    }



}
