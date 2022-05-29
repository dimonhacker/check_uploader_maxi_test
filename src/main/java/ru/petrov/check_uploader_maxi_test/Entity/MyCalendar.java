package ru.petrov.check_uploader_maxi_test.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;


public class MyCalendar {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar calendar;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
