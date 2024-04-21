package com.manufacturing.manufacturingmanagementsystem.service.MasterProductionSchedules;

import com.manufacturing.manufacturingmanagementsystem.repositories.MasterProductionSchedulesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterProductionSchedulesServices implements IMasterProductionSchedulesServices {

    private final MasterProductionSchedulesRepository masterProductionSchedulesRepository;

    // Các phương thức service khác cần thiết
}

