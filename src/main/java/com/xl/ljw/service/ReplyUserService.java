package com.xl.ljw.service;

import com.xl.ljw.entity.ReplyUserEntity;
import com.xl.ljw.until.SupportPage;

public interface ReplyUserService {

    public Object saveReplyUser(ReplyUserEntity replyUserEntity);

    /**收到的回复*/
    public Object findByCoverUserId(Integer CoverUserID, SupportPage supportPage);
}
