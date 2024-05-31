package com.manufacturing.manufacturingmanagementsystem.service.SaleForecastDetails;

import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastDetailsEntity;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ISaleForecastDetailsServices {
    // Author: Nguyen Cao Nhan
    // Service insert SaleForecastDetail
    List<Map<String, Object>> insertSaleForecastDetail(Long sale_id, List<Long> pids, List<Integer> quantities);
    // Author: Nguyen Cao Nhan
    // Service find SaleForecastDetail by id
    List<Map<String, Object>> findSaleForecastDetailById(Long id);
    // Author: Nguyen Cao Nhan
    // Service find SaleForecastDetail by pid and sale_id
    public SaleForecastDetailsEntity findSaleForecastDetailByPid_SaleID(Long pid, Long sale_id);
    // Author: Nguyen Cao Nhan
    // Service delete SaleForecastDetail
    public void deleteSaleForecastDetail(Long pid, Long sale_id);
    // Author: Nguyen Cao Nhan
    // Service update SaleForecastDetail
    public Map<String, Object> updateSaleForecastDetail(Long sale_id, Long pid, Integer quantity,float totalPrice,float totalSaleprice);
    // Author: Nguyen Cao Nhan
    // Service get all SaleForecastDetail
    public List< Object[]> findQuantityAndSaleForecastIdByProductIdAndMonthYear(Long productId, Date startDate, Date endDate);
    // Author: Nguyen Cao Nhan
    // Service get all SaleForecastDetail have total price
    Map<String, Object> findSumReportSaleForecastById(Long id);
}

