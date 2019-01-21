package com.xl.ljw.service;

import com.xl.ljw.entity.ReplyEntity;
import com.xl.ljw.until.SupportPage;

public interface ReplyService {

    /**查找所有回复*/
   public Object findReply(ReplyEntity replyEntity,SupportPage supportPage);

    /**回复该文章*/
   public Object saveReply(ReplyEntity replyEntity);

   /**自己回复*/
   public Object findUserReply(Integer userId);
}
