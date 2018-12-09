package com.xl.ljw.until;

import lombok.Data;

@Data
public class ResultResponse {

    private int code;

    private String msg;

    private Object data;

    private int count;

    public  ResultResponse(){


    }

    public  ResultResponse (int code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultResponse resultResponse(int code,String msg,Object data){
        ResultResponse resultResponse = new ResultResponse(code,msg,data);
        return resultResponse;
    }
    public static ResultResponse result(int code,String msg,Object data,int count){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(code);
        resultResponse.setData(data);
        resultResponse.setMsg(msg);
        resultResponse.setCount(count);
        return resultResponse;
    }

}
