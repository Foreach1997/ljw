package com.xl.ljw.until;

import lombok.Data;

@Data
public class ResultResponse {

    private int code;

    private String msg;

    private Object data;

    private int count;

    public static ResultResponse resultResponse(int code,String msg,Object data){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(code);
        resultResponse.setData(data);
        resultResponse.setMsg(msg);
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
