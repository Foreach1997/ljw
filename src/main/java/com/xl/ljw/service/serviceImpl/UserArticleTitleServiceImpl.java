package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.UserArticleTitleRepository;
import com.xl.ljw.entity.UserArticleEntity;
import com.xl.ljw.entity.UserArticleTitleEntity;
import com.xl.ljw.service.UserArticleTitleService;
import com.xl.ljw.until.ResultResponse;
import com.xl.ljw.until.SupportPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserArticleTitleServiceImpl implements UserArticleTitleService {

    @Autowired
    private UserArticleTitleRepository userArticleTitleRepository;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Object findAllUserArticle(Integer userId, SupportPage supportPage) {

        Pageable pageable = PageRequest.of(supportPage.getCurrentPage()-1,5, Sort.by(Sort.Order.desc("createTime")));

        List<UserArticleTitleEntity> userArticleEntities = userArticleTitleRepository.findByUserIdAndDelFlag(userId,0,pageable);

        int count = userArticleTitleRepository.findByUserIdAndDelFlag(userId,0).size();

        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();

        for (UserArticleTitleEntity userArticleTitleEntity:userArticleEntities){
           JSONObject jsonObject = new JSONObject();

           jsonObject.put("userArticleTitleId",userArticleTitleEntity.getUserArticleTitleId());
           jsonObject.put("userArticleTitle",userArticleTitleEntity.getUserArticleTitle());
           jsonObject.put("userArticleId",userArticleTitleEntity.getUserArticleId());
           jsonObject.put("userArticleNote",userArticleTitleEntity.getUserArticleNote());
           jsonObject.put("visitCount",userArticleTitleEntity.getVisitCount());
           jsonObject.put("agree",userArticleTitleEntity.getAgree());
           jsonObject.put("createTime",format.format(userArticleTitleEntity.getCreateTime()));
           jsonObject.put("userId",userArticleTitleEntity.getUserId());
           jsonObjects.add(jsonObject);
        }

        return ResultResponse.result(200,"请求成功",jsonObjects,count);
    }

    @Override
    public Object saveUserArticle(UserArticleTitleEntity userArticleTitleEntity) {

     userArticleTitleRepository.save(userArticleTitleEntity);

     return ResultResponse.resultResponse(200,"请求成功",null);
    }

    @Override
    public Object updateVisitCount(Integer userArticleId) {

        UserArticleTitleEntity userArticleTitleEntity = userArticleTitleRepository.findByUserArticleIdAndDelFlag(userArticleId,0);

        userArticleTitleEntity.setVisitCount(userArticleTitleEntity.getVisitCount()+1);

        userArticleTitleRepository.save(userArticleTitleEntity);

      return ResultResponse.resultResponse(200,"请求成功",null);
    }

    @Override
    public Object updateAgree(Integer userArticleId) {

        UserArticleTitleEntity userArticleTitleEntity = userArticleTitleRepository.findByUserArticleIdAndDelFlag(userArticleId,0);

        userArticleTitleEntity.setAgree(userArticleTitleEntity.getAgree()+1);

        userArticleTitleRepository.save(userArticleTitleEntity);

        return ResultResponse.resultResponse(200,"请求成功",null);
    }
}
