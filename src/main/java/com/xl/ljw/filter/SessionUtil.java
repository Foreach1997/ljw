package com.xl.ljw.filter;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Component
public class SessionUtil {


    @Async
    public void setSC() throws Exception{
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName()+"  123");
    }



    @Async
    public void setSYC(){

        System.out.println(Thread.currentThread().getName()+"  456");
    }


}
