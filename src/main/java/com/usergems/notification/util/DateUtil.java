package com.usergems.notification.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date convertToDateFromString(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date formatTodayDate(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date todayDate = new Date();
        try {
            return simpleDateFormat.parse(simpleDateFormat.format(todayDate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
