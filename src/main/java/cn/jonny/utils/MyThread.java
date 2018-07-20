package cn.jonny.utils;

import java.util.Calendar;
import java.util.concurrent.ThreadPoolExecutor;

public class MyThread{
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int min = calendar.get(Calendar.MINUTE);
        int min1 = calendar.getTime().getMinutes();
        System.out.println(min+" "+min1);
    }
}
