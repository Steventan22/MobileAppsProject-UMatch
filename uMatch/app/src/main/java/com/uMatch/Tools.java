package com.uMatch;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
    public static String getTime (Long Time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        return simpleDateFormat.format(new Date(Time));
    }
}
