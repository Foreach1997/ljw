package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.*;
import com.xl.ljw.entity.UserEntity;
import com.xl.ljw.service.UserService;
import com.xl.ljw.until.MD5;
import com.xl.ljw.until.ResultResponse;
import com.xl.ljw.until.SystemContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleTitleRepository articleTitleRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private CommunicationRepository communicationRepository;

    @Autowired
    private ReplyUserRepository replyUserRepository;

    @Autowired
    private UserArticleReplyRepository userArticleReplyRepository;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SimpleDateFormat sortFormat = new SimpleDateFormat("MM-dd HH:mm:ss");

    @Override
    public Object userLogin(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response) {

        userEntity.setUserPassword(MD5.Md5Until(userEntity.getUserPassword()));
        Example<UserEntity> example = Example.of(userEntity);
        HttpSession session =  request.getSession();
        List<UserEntity> user = userRepository.findAll(example);

        if (!user.isEmpty()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId",user.get(0).getUserId());
            jsonObject.put("sex",user.get(0).getSex()==1?"男":"女");
            jsonObject.put("name",user.get(0).getName());
            jsonObject.put("email",user.get(0).getEmail());
            jsonObject.put("photo",user.get(0).getPhoto());
            jsonObject.put("loginCount",user.get(0).getLoginCount());
            UserEntity userA=userRepository.getOne(user.get(0).getUserId());
            userA.setLoginCount(userA.getLoginCount()+1);
            userA.setLoginTime(new Date());
            userRepository.save(userA);

            session.setAttribute("userId",user.get(0).getUserId());
           // HttpSession session =  request.getSession();
            //session.setAttribute("userId",1);
            // session.setMaxInactiveInterval(18);
            //System.out.println(request.getSession().getAttribute("userId"));
            Cookie cookie = new Cookie("IsFlag","true");
            Cookie sessionId = new Cookie("sessionId",session.getId());
            sessionId.setPath("/");
            cookie.setPath("/");
            // cookie.setMaxAge(20);
            //sessionId.setMaxAge(10);
            response.addCookie(cookie);
            response.addCookie(sessionId);


            return ResultResponse.resultResponse(200,"登陆成功",jsonObject);
        }else {
            return ResultResponse.resultResponse(250,"请检查用户名或密码",null);
        }
    }

    @Override
    public int userReg(UserEntity userEntity) {

        String userName = userRepository.findByUserName(userEntity.getUserName());
        if ("".equals(userEntity.getUserName()) || userEntity.getUserName()==null){
            return 5;
        }
        if (!userEntity.getUserName().equals(userName)) {
            userEntity.setPhoto(SystemContent.photo);
            userEntity.setSex(1);
            userEntity.setLoginCount(0);
            userEntity.setUserPassword(MD5.Md5Until(userEntity.getPassword()));
            try {
                userRepository.save(userEntity);
                return 1;
            } catch (Exception e) {
                return 0;
            }
        }
        return 2;
    }

    @Override
    public Object qiut(HttpServletRequest request, HttpServletResponse response) {

        Object userId = request.getSession().getAttribute("userId");

        if (userId!=null && !"".equals(userId)) {
            request.getSession().removeAttribute("userId");
            Cookie cookie = new Cookie("IsFlag", "false");
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println("退出成功");
            return ResultResponse.resultResponse(200,"退出成功",null);
        }
        return ResultResponse.resultResponse(300,"退出失败",null);
    }

    @Override
    public Object findVisitor() {
        Pageable pageable = PageRequest.of(0,16, Sort.by(Sort.Order.desc("loginTime")));

        List<JSONObject> userEntities = userRepository.findAllVisitor();

        List<JSONObject> userEntityList = new ArrayList<JSONObject>();
        for (JSONObject user:userEntities){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",user.getString("name"));
            jsonObject.put("photo",user.getString("photo"));
            jsonObject.put("loginCount",user.getInteger("login_count"));
            jsonObject.put("loginTime",format.format(user.getDate("login_time")));
            jsonObject.put("userId",user.getInteger("user_id"));
            userEntityList.add(jsonObject);
        }
        return ResultResponse.resultResponse(200,"请求成功",userEntityList);
    }

    @Override
    public Object updatePhoto(MultipartFile file, UserEntity userEntity) {
        //String path = "/www/tom/apache-tomcat-8.5.35/webapps/img";
        //String path = "D://BaiduNetdiskDownload//Vue";
        String path = SystemContent.IMG_ADDRESS;
        String name = UUID.randomUUID().toString() +file.getOriginalFilename();
        System.out.println(name);
        System.out.println(file.getName());
        File newPath = new File(path,name);
        if (!file.isEmpty()){
            try {
                InputStream in = file.getInputStream();
                FileOutputStream out = new FileOutputStream(newPath);
                byte buffer[] = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) > 0) {

                    out.write(buffer, 0, len);
                }
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                return ResultResponse.resultResponse(250,"上传失败",null);
            }
        }

        String photo = SystemContent.HTTP_ADDRESS+"/img/"+name;
        String userName = userEntity.getName();
        Integer userId = userEntity.getUserId();
        String  password= userEntity.getPassword();
        String userPassword = MD5.Md5Until(userEntity.getUserPassword());
        UserEntity  user = userRepository.getByUserIdAndDelFlag(userId,0);
        try {
        if (!userPassword.equals(user.getUserPassword())){
        articleRepository.updatePhotoAndName(photo,userId);
        articleTitleRepository.updatePhotoAndName(photo,userId,userName);
        userRepository.updatePhotoAndName(photo,userId,userName,password,userPassword);
        replyRepository.updatePhotoAndName(photo,userId,userName);
        communicationRepository.updatePhotoAndName(userId,userName);
        replyUserRepository.updatePhotoAndName(photo,userId,userName);
        userArticleReplyRepository.updatePhotoAndName(photo,userId,userName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("src",SystemContent.HTTP_ADDRESS+"/img/"+name);
        jsonObject.put("title",name);
        jsonObject.put("name",userName);
        jsonObject.put("photo",photo);
        return ResultResponse.resultResponse(0,"上传成功",jsonObject);
        }else {
            return ResultResponse.resultResponse(250,"请勿输入与上次一样的密码",null);

            }
        }catch (Exception ex){
            return ResultResponse.resultResponse(300,"上传失败",null);
        }
    }
}
