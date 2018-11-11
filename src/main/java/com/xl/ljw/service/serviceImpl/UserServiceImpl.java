package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.UserRepository;
import com.xl.ljw.entity.UserEntity;
import com.xl.ljw.service.UserService;
import com.xl.ljw.until.MD5;
import com.xl.ljw.until.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public Object userLogin(String userName, String userPassword) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setUserPassword(MD5.Md5Until(userPassword));

        Example<UserEntity> example = Example.of(userEntity);

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
            return ResultResponse.resultResponse(200,"登陆成功",jsonObject);
        }else {
            return ResultResponse.resultResponse(250,"登陆失败",null);
        }
    }
}
