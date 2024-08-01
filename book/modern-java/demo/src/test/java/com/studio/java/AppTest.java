package com.studio.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    void validaData() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date inicio = sdf.parse("2020-09-01");
        Date fim = sdf.parse("2021-03-23");
        boolean result = app.isWithinSixMonths(inicio,fim);
        assertTrue(result);
    }


    @DisplayName("Test case where the end date is within the next 6 months")
    @Test
    void testIsWithinSixMonths_EndDateWithinSixMonths() {
        // Arrange
        Date startDate = new Date();
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(startDate);
        endDate.add(Calendar.MONTH, 5);

        // Act
        boolean result = app.isWithinSixMonths(startDate, endDate.getTime());

        // Assert
        assertTrue(result);
    }

    @DisplayName("Test case where the end date is exactly 6 months after the start date")
    @Test
    void testIsWithinSixMonths_EndDateExactlySixMonthsAfterStartDate() {
        // Arrange
        Date startDate = new Date();
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(startDate);
        endDate.add(Calendar.MONTH, 6);

        // Act
        boolean result = app.isWithinSixMonths(startDate, endDate.getTime());

        // Assert
        assertTrue(result);
    }

    @DisplayName("Test case where the end date is more than 6 months after the start date")
    @Test
    void testIsWithinSixMonths_EndDateMoreThanSixMonthsAfterStartDate() {
        // Arrange
        Date startDate = new Date();
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(startDate);
        endDate.add(Calendar.MONTH, 7);

        // Act
        boolean result = app.isWithinSixMonths(startDate, endDate.getTime());

        // Assert
        assertFalse(result);
    }

    @DisplayName("Test case where the end date is before the start date")
    @Test
    void testIsWithinSixMonths_EndDateBeforeStartDate() {
        // Arrange
        Date startDate = new Date();
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(startDate);
        endDate.add(Calendar.MONTH, -1);

        // Act
        boolean result = app.isWithinSixMonths(startDate, endDate.getTime());

        // Assert
        assertFalse(result);
    }

    @DisplayName("Test case where the start date and end date are the same")
    @Test
    void testIsWithinSixMonths_StartAndEndDateAreTheSame() {
        // Arrange
        Date startDate = new Date();

        // Act
        boolean result = app.isWithinSixMonths(startDate, startDate);

        // Assert
        assertTrue(result);
    }


}