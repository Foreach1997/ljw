package com.xl.ljw.service;

import com.xl.ljw.entity.ArticleEntity;
import com.xl.ljw.entity.ArticleTitleEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ArticleService {

    /**发表文章*/
    public  Object report(ArticleEntity articleEntity, ArticleTitleEntity articleTitleEntity);

   /**查看文章*/
    public  Object findArticle(ArticleEntity articleEntity);

    /**上传照片*/
    public Object uploadPhoto(MultipartFile  File);


}
