package com.manufacturing.manufacturingmanagementsystem.dtos.SaleForecast;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastsEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SaleForecastMonth extends SaleForecastDecorator {

    public SaleForecastMonth(ISaleForecast iSaleForecast) {
        super(iSaleForecast);
    }

    public List<SaleForecastsEntity> getAllSaleForecast(int month) {
        return getAllSaleForecastByMonth(month, super.getAllSaleForecast());
    }

    private List<SaleForecastsEntity> getAllSaleForecastByMonth(int month, List<SaleForecastsEntity> forecastsEntityMonthList) {
        List<SaleForecastsEntity> forecastsByMonth = new ArrayList<>();
        for (SaleForecastsEntity forecast : forecastsEntityMonthList) {
            // Assuming SaleForecastsEntity has get methods for dateStart and dateEnd
            Date dateStart = forecast.getDateStart();
            Date dateEnd = forecast.getDateEnd();

            // Check if both dateStart and dateEnd fall within the specified month
            if (isDateWithinMonth(dateStart, month) || isDateWithinMonth(dateEnd, month)) {
                forecastsByMonth.add(forecast);
            }
        }
        return forecastsByMonth;
    }

    private boolean isDateWithinMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) == month;
    }
}
