package com.example.SpringBootREST.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility Class
 * Contains all the utilities/helper we can use in the application ~

 * @author Eejay Taa
 */

public class DateUtils {

    public static String YYYY_MM_DD_HH_MM = "yyyy/MM/dd HH:mm";

    public static final String toStringYMDHM(final Date date){
        return parseDateToStr(YYYY_MM_DD_HH_MM, date);
    }

    public static final String parseDateToStr(final String format, final Date date){
        return new SimpleDateFormat(format).format(date);
    }
}
