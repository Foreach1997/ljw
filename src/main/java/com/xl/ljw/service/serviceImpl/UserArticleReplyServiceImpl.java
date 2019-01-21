package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xl.ljw.dao.UserArticleReplyRepository;
import com.xl.ljw.entity.UserArticleReplyEntity;
import com.xl.ljw.service.UserArticleReplyService;
import com.xl.ljw.until.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserArticleReplyServiceImpl implements UserArticleReplyService {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public UserArticleReplyRepository userArticleReplyRepository;

    @Override
    public Object findUserArticleReply(Integer userArticleId) {
        List<UserArticleReplyEntity> userArticleReplyEntities = userArticleReplyRepository.findByUserArticleIdAndDelFlag(userArticleId,0);

        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();

        for (UserArticleReplyEntity userArticleReplyEntity:userArticleReplyEntities){
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("createTime",format.format(userArticleReplyEntity.getCreateTime()));
            jsonObject.put("name",userArticleReplyEntity.getName());
            jsonObject.put("photo",userArticleReplyEntity.getPhoto());
            jsonObject.put("replyText",userArticleReplyEntity.getReplyText());
            jsonObject.put("userArticleId",userArticleReplyEntity.getUserArticleId());
            jsonObject.put("userId",userArticleReplyEntity.getUserId());
            jsonObject.put("userArticleReplyId",userArticleReplyEntity.getUserArticleReplyId());
            jsonObjects.add(jsonObject);
        }
        return ResultResponse.resultResponse(200,"请求成功",jsonObjects);
    }

    @Override
    public Object saveUserArticleReply(UserArticleReplyEntity userArticleReplyEntity) {
        userArticleReplyEntity.setCreateTime(new Date());
        userArticleReplyRepository.save(userArticleReplyEntity);
        return ResultResponse.resultResponse(200,"请求成功",null);
    }
}
