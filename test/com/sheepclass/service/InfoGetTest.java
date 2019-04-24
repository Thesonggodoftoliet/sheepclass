package com.sheepclass.service;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class InfoGetTest {

    @Test
    public void getWeeklogintimes() {
        Date  date = new Date();
        System.out.println(date.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE,-1);
        System.out.println(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE,2);
        System.out.println(calendar.getFirstDayOfWeek()+" "+calendar.getTime().getDay());
        calendar.add(Calendar.DATE,-calendar.getTime().getDay());
        System.out.println(calendar.getTime());
    }
}