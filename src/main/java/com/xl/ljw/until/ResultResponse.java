package com.xl.ljw.until;

import lombok.Data;

@Data
public class ResultResponse {

    private int code;

    private String msg;

    private Object data;

    public static ResultResponse resultResponse(int code,String msg,Object data){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(code);
        resultResponse.setData(data);
        resultResponse.setMsg(msg);
        return resultResponse;
    }

}
