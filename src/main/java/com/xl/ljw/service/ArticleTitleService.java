package com.xl.ljw.service;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.entity.ArticleTitleEntity;
import com.xl.ljw.until.SupportPage;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleTitleService {

    /**最新发表的四篇文章*/
    public List<JSONObject> findArticleTitles();

    /**按需获得所有文章*/
    public Object findAllArticleTitles(ArticleTitleEntity articleTitleEntity, SupportPage supportPage);

}
