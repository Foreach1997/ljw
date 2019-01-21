package com.xl.ljw.service;

import com.xl.ljw.entity.UserArticleTitleEntity;
import com.xl.ljw.until.SupportPage;

import java.util.List;

public interface UserArticleTitleService {

    //查找主页所有的动态
    public Object findAllUserArticle(Integer userId, SupportPage supportPage);

    //发表动态标题
    public Object saveUserArticle(UserArticleTitleEntity userArticleTitleEntity);

    //访问量
    public Object updateVisitCount(Integer userArticleTitleId);

    //点赞
    public Object updateAgree(Integer userArticleTitleId);
}
