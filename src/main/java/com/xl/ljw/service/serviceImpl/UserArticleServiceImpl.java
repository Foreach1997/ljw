package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.UserArticleRepository;
import com.xl.ljw.dao.UserArticleTitleRepository;
import com.xl.ljw.entity.UserArticleEntity;
import com.xl.ljw.entity.UserArticleTitleEntity;
import com.xl.ljw.service.UserArticleService;
import com.xl.ljw.until.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class UserArticleServiceImpl implements UserArticleService {

    @Autowired
    private UserArticleRepository userArticleRepository;

    @Autowired
    private UserArticleTitleRepository userArticleTitleRepository;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public UserArticleEntity saveUserArticle(UserArticleEntity userArticleEntity) {

       userArticleEntity = userArticleRepository.save(userArticleEntity);

       return userArticleEntity;

    }

    @Override
    public Object findUserArticle(Integer UserArticleId) {
       UserArticleEntity userArticleEntity = userArticleRepository.findByUserArticleIdAndDelFlag(UserArticleId,0);

        UserArticleTitleEntity userArticleTitleEntity = userArticleTitleRepository.findByUserArticleIdAndDelFlag(UserArticleId,0);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("createTime",format.format(userArticleEntity.getCreateTime()));
        jsonObject.put("userId",userArticleEntity.getUserId());
        jsonObject.put("userArticleId",userArticleEntity.getUserArticleId());
        jsonObject.put("userArticleText",userArticleEntity.getUserArticleText());
        jsonObject.put("agree",userArticleTitleEntity.getAgree());
        jsonObject.put("userArticleTitle",userArticleTitleEntity.getUserArticleTitle());
        jsonObject.put("visitCount",userArticleTitleEntity.getVisitCount());

        return ResultResponse.resultResponse(200,"请求成功",jsonObject);
    }
}
