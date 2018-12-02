package com.xl.ljw.service.serviceImpl;

import com.xl.ljw.dao.ReplyUserRepository;
import com.xl.ljw.dao.UserRepository;
import com.xl.ljw.entity.ReplyUserEntity;
import com.xl.ljw.service.ReplyUserService;
import com.xl.ljw.until.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
