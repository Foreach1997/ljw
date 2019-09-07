package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.ArticleRepository;
import com.xl.ljw.dao.ArticleTitleRepository;
import com.xl.ljw.dao.ReplyRepository;
import com.xl.ljw.entity.ArticleTitleEntity;
import com.xl.ljw.entity.CommunicationEntity;
import com.xl.ljw.service.ArticleTitleService;
import com.xl.ljw.until.ResultResponse;
import com.xl.ljw.until.SupportPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleTitleServiceImpl implements ArticleTitleService {


    @Autowired
    private ArticleTitleRepository articleTitleRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ReplyRepository replyRepository;


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public List<JSONObject> findArticleTitles() {
       List<ArticleTitleEntity> articleTitleEntities = articleTitleRepository.findArticleTitleAll();

        List<JSONObject> list = new ArrayList<JSONObject>();

       for (ArticleTitleEntity entities : articleTitleEntities){
           JSONObject jsonObject = new JSONObject();

           int count = replyRepository.findReplyCount(entities.getArticleId());

           jsonObject.put("articleTitleId",entities.getArticleTitleId());
           jsonObject.put("articleId",entities.getArticleId());
           jsonObject.put("articleTitle",entities.getArticleTitle());
           jsonObject.put("articleType",entities.getArticleType());
           jsonObject.put("userId",entities.getUserId());
           jsonObject.put("name",entities.getName());
           try {
               jsonObject.put("createTime",format.format(entities.getCreateTime()));
           } catch (Exception e) {
               e.printStackTrace();
           }
           jsonObject.put("replyCount",count);
           jsonObject.put("photo",entities.getPhoto());

           list.add(jsonObject);
       }
       return list;
    }

    @Override
    public Object findAllArticleTitles(ArticleTitleEntity articleTitleEntity, SupportPage supportPage) {

        Pageable pageable = PageRequest.of(supportPage.getCurrentPage()-1,8, Sort.by(Sort.Order.desc("createTime")));

        int count = 0;

        Example<ArticleTitleEntity> example = Example.of(articleTitleEntity);
        List<ArticleTitleEntity> articleTitleEntities = null;
        if (articleTitleEntity.getArticleType()==null||"".equals(articleTitleEntity.getArticleType())) {
            articleTitleEntities = articleTitleRepository.findByDelFlag(0,pageable);
            count = articleTitleRepository.findAllArticleTitleCount();
        }else {
            articleTitleEntities = articleTitleRepository.findAll(example, pageable).getContent();
            count = articleTitleRepository.findAll(example).size();
        }
        System.out.println(articleTitleEntities);
        List<JSONObject> list = new ArrayList<JSONObject>();
        for (ArticleTitleEntity entities : articleTitleEntities){
            JSONObject json = new JSONObject();
            int replyCount = articleTitleRepository.findReplyCount(entities.getArticleId());
            int browseCount = articleTitleRepository.findBrowseCount(entities.getArticleId());
            //System.out.println(jsonObject);
            //json.put("replyCount",jsonObject!=null ? jsonObject.getInteger("replyCount"):0);
            //json.put("browseCount",jsonObject!=null ? jsonObject.getInteger("browseCount"):0);
            json.put("replyCount",replyCount);
            json.put("browseCount",browseCount);
            json.put("userId",entities.getUserId());
            json.put("name",entities.getName());
            json.put("photo",entities.getPhoto());
                json.put("createTime",format.format(entities.getCreateTime()));

            json.put("articleId",entities.getArticleId());
            json.put("articleTitle",entities.getArticleTitle());
            json.put("articleTitleId",entities.getArticleTitleId());
            json.put("articleType",entities.getArticleType());
            list.add(json);
    }
        System.out.println(count);
        return ResultResponse.result(200,"请求成功",list,count);
    }

    @Override
    public Object findHostArticleTitle() {
      List<JSONObject> jsonObjects = articleTitleRepository.findHostArticleTitle();
      return ResultResponse.resultResponse(200,"请求成功",jsonObjects);
    }

    @Override
    public Object findUserArticleTitle(Integer userId,SupportPage supportPage) {

        Pageable pageable = PageRequest.of(supportPage.getCurrentPage()-1,5, Sort.by(Sort.Order.desc("createTime")));

        List<ArticleTitleEntity> articleTitleEntities = articleTitleRepository.findByUserIdAndDelFlag(userId,0,pageable);

        int count = articleTitleRepository.findByUserIdAndDelFlag(userId,0).size();

         List<JSONObject> jsonObjects = new ArrayList<JSONObject>();

        for (ArticleTitleEntity articleTitleEntity : articleTitleEntities){
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("createTime",format.format(articleTitleEntity.getCreateTime()));
            jsonObject.put("articleId",articleTitleEntity.getArticleId());
            jsonObject.put("articleTitle",articleTitleEntity.getArticleTitle());
            jsonObjects.add(jsonObject);
        }

        return ResultResponse.result(200,"请求成功",jsonObjects,count);
    }

    @Override
    public Object delUserArticleTitle(Integer articleId) {

        articleTitleRepository.updateUserArticleTitle(articleId);

        articleRepository.updateUserArticleTitle(articleId);

        return ResultResponse.resultResponse(200,"请求成功",null);
    }
}
