package com.xl.ljw.until;

public class SystemContent {

    public final static String photo = "http://47.107.55.207:8019/img/9e7a853693e8697ba1f97e869cdacbe0.jpg";

    public final static String IMG_ADDRESS = "/usr/img";

    public final static String HTTP_ADDRESS = "http://47.107.55.207:8019";

    public static void main(String[] args) {
        System.out.println(photo.split("/")[4]);
    }

}
