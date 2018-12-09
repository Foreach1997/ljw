package com.xl.ljw.controller;

import com.xl.ljw.entity.CommunicationEntity;
import com.xl.ljw.service.serviceImpl.CommunicationServiceImpl;
import com.xl.ljw.until.SupportPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/communication")
public class CommunicationController {

    @Autowired
    private CommunicationServiceImpl communicationServiceImpl;

    @GetMapping("/findAllCommunication")
    public Object findAllCommunication(SupportPage supportPage){

        return communicationServiceImpl.findAllCommunication(supportPage);
    }

    @PostMapping("/saveCommunication")
    public Object save(CommunicationEntity communicationEntity){
      return  communicationServiceImpl.saveCommunication(communicationEntity);
    }


}
