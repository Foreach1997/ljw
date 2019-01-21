package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.ReplyRepository;
import com.xl.ljw.dao.ReplyUserRepository;
import com.xl.ljw.dao.UserRepository;
import com.xl.ljw.entity.ReplyEntity;
import com.xl.ljw.entity.ReplyUserEntity;
import com.xl.ljw.entity.UserEntity;
import com.xl.ljw.service.ReplyService;
import com.xl.ljw.until.ResultResponse;
import com.xl.ljw.until.SupportPage;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyUserRepository replyUserRepository;

    @Autowired
    private UserRepository userRepository;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public Object findReply(ReplyEntity replyEntity,SupportPage supportPage) {

        Pageable pageable = PageRequest.of(supportPage.getCurrentPage()-1,8, Sort.by(Sort.Order.desc("createTime")));

        Example<ReplyEntity> replyEntityExample = Example.of(replyEntity);

        int count = replyRepository.findAll(replyEntityExample).size();
        List<ReplyEntity> replyEntities =  replyRepository.findAll(replyEntityExample,pageable).getContent();

        for (ReplyEntity reply : replyEntities) {
            List<ReplyUserEntity> replyUserEntities = replyUserRepository.findByReplyIdAndDelFlag(reply.getReplyId(),0,Sort.by(Sort.Order.desc("createTime")));
          /* if (!replyUserEntities.isEmpty()){*/
               reply.setReplyUserEntity(replyUserEntities);
              /* for (ReplyUserEntity replyUser:replyUserEntities){
               UserEntity userEntity = userRepository.findByUserIdAndDelFlag(replyUser.getUserId(),0);
               replyUser.setUserEntity(userEntity);
               }*/

        }
        return ResultResponse.result(200,"请求成功",replyEntities,count);
    }

    @Override
    public Object saveReply(ReplyEntity replyEntity) {
        replyEntity.setCreateTime(format.format(new Date()));
        replyRepository.save(replyEntity);
        return ResultResponse.resultResponse(200,"回复成功",null);
    }

    @Override
    public Object findUserReply(Integer userId) {
        List<JSONObject> replyEntities = replyRepository.findByUserIdAndDelFlag(userId);

        /*List<JSONObject> jsObjects = new ArrayList<JSONObject>();

        for (ReplyEntity replyEntity:replyEntities){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("replyId",replyEntity.getReplyId());
            jsonObject.put("text",replyEntity.getText());
            jsonObject.put("name",replyEntity.getName());
            jsonObject.put("createTime",replyEntity.getCreateTime());
            jsonObject.put("articleId",replyEntity.getArticleId());
            jsObjects.add(jsonObject);
        }*/
        return ResultResponse.resultResponse(200,"请求成功",replyEntities);
    }


}
