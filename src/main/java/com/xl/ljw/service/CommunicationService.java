package com.xl.ljw.service;

import com.xl.ljw.entity.CommunicationEntity;
import com.xl.ljw.until.SupportPage;

public interface CommunicationService {

    /**获取所有留言*/
    public Object findAllCommunication(SupportPage supportPage);

    /**保存留言*/
    public Object saveCommunication(CommunicationEntity communicationEntity);
}
