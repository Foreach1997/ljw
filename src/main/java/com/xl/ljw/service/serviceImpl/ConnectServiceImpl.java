package com.xl.ljw.service.serviceImpl;

import com.xl.ljw.dao.ConnectRepository;
import com.xl.ljw.entity.ConnectEntity;
import com.xl.ljw.service.ConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectServiceImpl implements ConnectService {

    @Autowired
    private ConnectRepository connectRepository;

    @Override
    public List<ConnectEntity> findAll() {
        return connectRepository.findAllDesc();

    }
}
