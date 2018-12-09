package com.xl.ljw.until;

import lombok.Data;

/**
 * 分页工具类
 */
@Data
public class SupportPage {

    //当前页
    private  Integer currentPage = 1;

    //页码大小
    private  Integer PageSize;

}
