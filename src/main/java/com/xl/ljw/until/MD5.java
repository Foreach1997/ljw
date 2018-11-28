package com.xl.ljw.until;

import org.apache.commons.codec.digest.DigestUtils;
import sun.applet.Main;

public class MD5 {

    public static String Md5Until(String str){
     String key =  DigestUtils.md5Hex("xiaoLuo1997"+str);
        return key;
    }

    public static void main(String[] args) {
     String key =  Md5Until("123123");
        System.out.println(key);
        System.out.println(key.equals(Md5Until("qweqweqwe")));
    }

}
