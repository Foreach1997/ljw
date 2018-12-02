package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.UserRepository;
import com.xl.ljw.entity.UserEntity;
import com.xl.ljw.service.UserService;
import com.xl.ljw.until.MD5;
import com.xl.ljw.until.ResultResponse;
import com.xl.ljw.until.SystemContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

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
            return ResultResponse.resultResponse(250,"登陆失败",null);
        }
    }

    @Override
    public int userReg(UserEntity userEntity) {
        userEntity.setPhoto(SystemContent.photo);
        userEntity.setSex(1);
        userEntity.setLoginCount(0);
        userEntity.setUserPassword(MD5.Md5Until(userEntity.getPassword()));
        try {
            userRepository.save(userEntity);
            return 1;
        }catch (Exception e){
            return 0;
        }
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
}
