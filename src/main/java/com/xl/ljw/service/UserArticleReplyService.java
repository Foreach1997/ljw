package com.xl.ljw.service;

import com.xl.ljw.entity.UserArticleReplyEntity;

public interface UserArticleReplyService {

    //获取该动态下所有的回复
    public Object findUserArticleReply(Integer userArticleId);

    //回复该动态
    public Object saveUserArticleReply(UserArticleReplyEntity userArticleReplyEntity);

}
