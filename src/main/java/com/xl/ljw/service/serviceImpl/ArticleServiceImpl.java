package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.*;
import com.xl.ljw.entity.*;
import com.xl.ljw.service.ArticleService;
import com.xl.ljw.until.ResultResponse;
import com.xl.ljw.until.SystemContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleTitleRepository articleTitleRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyUserRepository replyUserRepository;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Object report(ArticleEntity articleEntity, ArticleTitleEntity articleTitleEntity) {

        articleEntity.setCreateTime(new Timestamp(new Date().getTime()));

        UserEntity userEntity = userRepository.getOne(articleEntity.getUserId());
        articleEntity = articleRepository.save(articleEntity);
        articleTitleEntity.setName(userEntity.getName());
        articleTitleEntity.setUserId(articleEntity.getUserId());
        articleTitleEntity.setCreateTime(new Timestamp(new Date().getTime()));
        articleTitleEntity.setArticleId(articleEntity.getArticleId());
        articleTitleRepository.save(articleTitleEntity);

       return ResultResponse.resultResponse(200,"保存成功",null);
    }

    @Override
    public Object findArticle(ArticleEntity articleEntity) {


        ArticleTitleEntity  articleTitleEntity = new ArticleTitleEntity();
        articleTitleEntity.setArticleId(articleEntity.getArticleId());

        /*ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setArticleId(articleEntity.getArticleId());
        Example<ReplyEntity> replyEntityExample = Example.of(replyEntity);*/

        Example<ArticleTitleEntity> articleExample = Example.of(articleTitleEntity);

       /* ReplyUserEntity replyUserEntity = new ReplyUserEntity();
        Example<ReplyUserEntity> replyUserEntityExample = Example.of(replyUserEntity);*/

        //
       // List<ReplyUserEntity> replyUserEntities = replyUserRepository.findAll(replyUserEntityExample);
        //获取该文章的所有回复
        //List<ReplyEntity> replyEntities =  replyRepository.findAll(replyEntityExample);
        //获取文章的标题
        ArticleTitleEntity articleTitleEntitie =  articleTitleRepository.findOne(articleExample).get();
        //获取文章
        ArticleEntity articleEntitys = articleRepository.findById(articleEntity.getArticleId()).get();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("articleTitleEntitie",articleTitleEntitie);
        jsonObject.put("articleEntitys",articleEntitys);

        return ResultResponse.resultResponse(200,"请求成功",jsonObject);
    }

    @Override
    public Object uploadPhoto(MultipartFile file) {
        //String path = "/www/tom/apache-tomcat-8.5.35/webapps/img";
        String path = SystemContent.IMG_ADDRESS;
        String name = UUID.randomUUID().toString()+file.getOriginalFilename();
        System.out.println(name);
        System.out.println(file.getName());
        File newPath = new File(path,name);
       if (!file.isEmpty()){
           try {
               InputStream in = file.getInputStream();
               FileOutputStream out = new FileOutputStream(newPath);
               byte buffer[] = new byte[1024];
               int len = 0;
               while ((len = in.read(buffer)) > 0) {

                   out.write(buffer, 0, len);
               }
               in.close();
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
               return ResultResponse.resultResponse(250,"上传失败",null);
           }
       }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("src",SystemContent.HTTP_ADDRESS+"/img/"+name);
        jsonObject.put("title",name);
        return ResultResponse.resultResponse(0,"上传成功",jsonObject);
    }


}
