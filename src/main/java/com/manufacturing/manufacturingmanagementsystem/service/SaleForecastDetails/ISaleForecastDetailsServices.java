package com.manufacturing.manufacturingmanagementsystem.service.SaleForecastDetails;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastDetailsEntity;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ISaleForecastDetailsServices {
    List<Map<String, Object>> insertSaleForecastDetail(Long sale_id, List<Long> pids, List<Integer> quantities);
    List<Map<String, Object>> findSaleForecastDetailById(Long id);
    public SaleForecastDetailsEntity findSaleForecastDetailByPid_SaleID(Long pid, Long sale_id);
    public void deleteSaleForecastDetail(Long pid, Long sale_id);
    public Map<String, Object> updateSaleForecastDetail(Long sale_id, Long pid, Integer quantity,float totalPrice,float totalSaleprice);
    public List< Object[]> findQuantityAndSaleForecastIdByProductIdAndMonthYear(Long productId, Date startDate, Date endDate);
    Map<String, Object> findSumReportSaleForecastById(Long id);
}

