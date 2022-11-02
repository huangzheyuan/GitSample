package com.example.smartcity.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class disposeTime {
    public static String disposeTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(c.getTime());
        return time;
    }
}
