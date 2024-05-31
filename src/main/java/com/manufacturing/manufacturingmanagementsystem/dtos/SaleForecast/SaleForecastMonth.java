package com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
// Author: Nguyen Cao Nhan
// this class is used to handle the SaleForecastMonth
public class SaleForecastMonth extends SaleForecastDecorator {

    public SaleForecastMonth(ISaleForecast saleForecast) {
        super(saleForecast);
    }

    public List<SaleForecastsEntity> getAllSaleForecast(int month, int year) {
        List<SaleForecastsEntity> all = super.getAllSaleForecast(month, year);
        return filterByMonthAndYear(month, year, all);
    }

    private List<SaleForecastsEntity> filterByMonthAndYear(int month, int year, List<SaleForecastsEntity> forecasts) {
        List<SaleForecastsEntity> filteredForecasts = new ArrayList<>();
        for (SaleForecastsEntity forecast : forecasts) {
            if (isMonthAndYearWithinDateRange(forecast.getDateStart(), forecast.getDateEnd(), month, year)) {
                filteredForecasts.add(forecast);
            }
        }
        return filteredForecasts;
    }

    private boolean isMonthAndYearWithinDateRange(Date dateStart, Date dateEnd, int month, int year) {
        if (dateStart == null || dateEnd == null) {
            return false; // handle null dates
        }

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(dateStart);
        int startMonth = startCalendar.get(Calendar.MONTH)+1;
        int startYear = startCalendar.get(Calendar.YEAR);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(dateEnd);
        int endMonth = endCalendar.get(Calendar.MONTH)+1;
        int endYear = endCalendar.get(Calendar.YEAR);

        // Check if the provided month and year fall within the date range of the sale forecast
        return (startYear <= year && endYear >= year && startMonth <= month && endMonth >= month);
    }
}