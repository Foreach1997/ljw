package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.ArticleTitleRepository;
import com.xl.ljw.dao.ReplyRepository;
import com.xl.ljw.entity.ArticleTitleEntity;
import com.xl.ljw.service.ArticleTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleTitleServiceImpl implements ArticleTitleService {


    @Autowired
    private ArticleTitleRepository articleTitleRepository;

    @Autowired
    private ReplyRepository replyRepository;

    private  SimpleDateFormat longSdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public List<JSONObject> findArticleTitles() {
       List<ArticleTitleEntity> articleTitleEntities = articleTitleRepository.findArticleTitleAll();

        List<JSONObject> list = new ArrayList<JSONObject>();

       for (ArticleTitleEntity entities : articleTitleEntities){
           JSONObject jsonObject = new JSONObject();

           int count = replyRepository.findReplyCount(entities.getArticleId());

           jsonObject.put("articleTitleId",entities.getArticleTitleId());
           jsonObject.put("ArticleId",entities.getArticleId());
           jsonObject.put("articleTitle",entities.getArticleTitle());
           jsonObject.put("articleType",entities.getArticleType());
           jsonObject.put("userId",entities.getUserId());
           jsonObject.put("name",entities.getName());
           jsonObject.put("createTime",longSdf.format(entities.getCreateTime()));
           jsonObject.put("replyCount",count);
           jsonObject.put("photo",entities.getPhoto());

           list.add(jsonObject);
       }
       return list;
    }

    @Override
    public List<JSONObject> findAllArticleTitles(ArticleTitleEntity articleTitleEntity) {

        Pageable pageable = PageRequest.of(0,1);

        Example<ArticleTitleEntity> example = Example.of(articleTitleEntity);

        List<ArticleTitleEntity> articleTitleEntities = articleTitleRepository.findAll(example,pageable).getContent();

        List<JSONObject> list = new ArrayList<JSONObject>();
        for (ArticleTitleEntity entities : articleTitleEntities){
            JSONObject json = new JSONObject();
            int count = replyRepository.findReplyCount(entities.getArticleId());
    }
        return null;
    }
}
