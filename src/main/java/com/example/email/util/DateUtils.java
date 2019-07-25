package com.example.email.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author <bu.liwen@chinaott.net>
 * @Description
 * @Date 2019-07-25 15:34
 */
public class DateUtils {

    public static String format(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
