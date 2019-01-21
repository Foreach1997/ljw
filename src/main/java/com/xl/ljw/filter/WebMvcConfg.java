package com.xl.ljw.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/userArticleReply/saveUserArticleReply")
               .addPathPatterns("/replyUser/saveReplyUser")
               .addPathPatterns("/reply/saveReply")
               .addPathPatterns("/communication/saveCommunication")
               .addPathPatterns("/ArticleTitle/delUserArticleTitle")
               .addPathPatterns("/article/report")
               .addPathPatterns("/userArticle/saveUserArticle");
    }
}
