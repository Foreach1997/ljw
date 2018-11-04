package com.xl.ljw;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LjwApplicationTests {

    @Test
    public void contextLoads() {
       String  url = "http://localhost:8080/stu/getAll";

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setHeader("Connection", "Close");
        //List<NameValuePair> nvps = new ArrayList<NameValuePair>();
       /* Map<String,String> map = new HashMap<String,String>();
        map.put("name","123");
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }*/
        JSONObject jsObject= new JSONObject();
        jsObject.put("info","123");
        jsObject.put("name","456");
        StringEntity stringEntity = new StringEntity(jsObject.toString(),"UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        try {
           // httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            // entity.setContentEncoding("UTF-8");
            System.out.println("请求地址："+url);
            //System.out.println("请求参数："+nvps.toString());
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
           // response.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
