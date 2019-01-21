package com.xl.ljw.service;

import com.xl.ljw.entity.UserArticleEntity;

import java.util.List;

public interface UserArticleService {
     //发表动态
     public Object saveUserArticle(UserArticleEntity userArticleEntity);

     //查找当前动态的内容
     public Object findUserArticle(Integer UserArticleId);

}
