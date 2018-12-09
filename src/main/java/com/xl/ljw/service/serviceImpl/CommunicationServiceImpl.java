package com.xl.ljw.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.xl.ljw.dao.CommunicationRepository;
import com.xl.ljw.entity.CommunicationEntity;
import com.xl.ljw.service.CommunicationService;
import com.xl.ljw.until.ResultResponse;
import com.xl.ljw.until.SupportPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommunicationServiceImpl  implements CommunicationService {

    @Autowired
    private CommunicationRepository communicationRepository;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Object findAllCommunication(SupportPage supportPage) {

        Pageable pageable = PageRequest.of(supportPage.getCurrentPage()-1,5, Sort.by(Sort.Order.desc("createTime")));

        List<CommunicationEntity> communicationEntities =  communicationRepository.findAll(pageable).getContent();

        int count = communicationRepository.findAll().size();

        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();

        for (CommunicationEntity communicationEntity:communicationEntities) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",communicationEntity.getName());
            jsonObject.put("text",communicationEntity.getText());
            jsonObject.put("createTime",format.format(communicationEntity.getCreateTime()));
            jsonObjects.add(jsonObject);
        }

        return ResultResponse.result(200,"请求成功",jsonObjects,count);
    }

    @Override
    public Object saveCommunication(CommunicationEntity communicationEntity) {
        communicationEntity.setCreateTime(new Date());

        communicationRepository.save(communicationEntity);

        return ResultResponse.resultResponse(200,"请求成功",null);
    }
}
