package com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SaleForecastYear extends SaleForecastDecorator {
    public SaleForecastYear(ISaleForecast iSaleForecast) {
        super(iSaleForecast);
    }

    public List<SaleForecastsEntity> getAllSaleForecast(int year) {
        return getAllSaleForecastByYear(year, super.getAllSaleForecast());
    }

    private List<SaleForecastsEntity> getAllSaleForecastByYear(int year, List<SaleForecastsEntity> forecastsEntityMonthList) {
        List<SaleForecastsEntity> forecastsByYear = new ArrayList<>();
        for (SaleForecastsEntity forecast : forecastsEntityMonthList) {
            // Assuming SaleForecastsEntity has get methods for dateStart and dateEnd
            Date dateStart = forecast.getDateStart();
            Date dateEnd = forecast.getDateEnd();

            // Check if both dateStart or dateEnd fall within the specified year
            if (isDateWithinYear(dateStart, year) || isDateWithinYear(dateEnd, year)) {
                forecastsByYear.add(forecast);
            }
        }
        return forecastsByYear;
    }

    private boolean isDateWithinYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) == year;
    }
}
