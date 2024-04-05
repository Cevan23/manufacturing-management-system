package com.manufacturing.manufacturingmanagementsystem.service.WorkOrders;

import com.manufacturing.manufacturingmanagementsystem.repositories.WorkOrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkOrdersServices implements IWorkOrdersServices {

    private final WorkOrdersRepository workOrdersRepository;

    // Các phương thức service khác cần thiết
}

