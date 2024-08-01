package com.studio.java;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Calendar;
import java.util.Date;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args) {
        logger.info("Hello world!");
    }

    boolean isWithinSixMonths(final Date start, final Date end) {


        Calendar startLimit = this.toCalendar(start);
        Calendar endDate = this.toCalendar(end);

        if(endDate.before(startLimit)) return false;

        startLimit.add(Calendar.MONTH, 6);
        return endDate.before(startLimit);
    }

    private Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}