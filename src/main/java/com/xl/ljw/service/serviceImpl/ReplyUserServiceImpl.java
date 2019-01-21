package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.ReplyUserRepository;
import com.xl.ljw.dao.UserRepository;
import com.xl.ljw.entity.ReplyUserEntity;
import com.xl.ljw.service.ReplyUserService;
import com.xl.ljw.until.ResultResponse;
import com.xl.ljw.until.SupportPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplyUserServiceImpl implements ReplyUserService {
    @Autowired
    private ReplyUserRepository replyUserRepository;

    @Autowired
    private UserRepository userRepository;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Object saveReplyUser(ReplyUserEntity replyUserEntity) {
            replyUserEntity.setCreateTime(format.format(new Date()));
            replyUserRepository.save(replyUserEntity);
            return ResultResponse.resultResponse(200,"回复成功",null);
    }

    @Override
    public Object findByCoverUserId(Integer CoverUserID, SupportPage supportPage) {
        //Pageable pageable = PageRequest.of(supportPage.getCurrentPage()-1,8, Sort.by(Sort.Order.desc("createTime")));

       /* List<ReplyUserEntity> replyUserEntities = replyUserRepository.findByCoverUserIdAndDelFlag(CoverUserID,0);

        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();

        for (ReplyUserEntity replyUserEntity:replyUserEntities) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("replyUserId",replyUserEntity.getReplyUserId());
            jsonObject.put("note",replyUserEntity.getNote());
            jsonObject.put("userId",replyUserEntity.getUserId());
            jsonObject.put("name",replyUserEntity.getName());
            jsonObject.put("articleId",replyUserEntity.getArticleId());
            jsonObject.put("replyId",replyUserEntity.getReplyId());
            jsonObject.put("createTime",format.format(replyUserEntity.getCreateTime()));
        }*/

        List<JSONObject> jsonObjects = replyUserRepository.findByCoverUserIdAndDelFlag(CoverUserID);

        return ResultResponse.resultResponse(200,"请求成功",jsonObjects);
    }
}
