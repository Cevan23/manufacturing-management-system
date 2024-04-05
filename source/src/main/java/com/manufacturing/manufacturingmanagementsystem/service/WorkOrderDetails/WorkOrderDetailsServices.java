package com.manufacturing.manufacturingmanagementsystem.service.WorkOrderDetails;

import com.manufacturing.manufacturingmanagementsystem.repositories.WorkOrderDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkOrderDetailsServices implements IWorkOrderDetailsServices {

    private final WorkOrderDetailsRepository workOrderDetailsRepository;

    // Các phương thức service khác cần thiết
}
