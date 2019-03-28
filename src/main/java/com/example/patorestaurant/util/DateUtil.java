package com.example.patorestaurant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("YYYY-MM-dd");

    public static String convertDateToString(Date date) {
        return SDF.format(date);
    }

    public static Date convertStringToDate(String date) {
        try {
            return SDF.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
