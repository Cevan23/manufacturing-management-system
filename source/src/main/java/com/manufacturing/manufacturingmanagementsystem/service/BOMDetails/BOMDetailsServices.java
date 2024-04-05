package com.manufacturing.manufacturingmanagementsystem.service.BOMDetails;

import com.manufacturing.manufacturingmanagementsystem.repositories.BOMDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BOMDetailsServices implements IBOMDetailsServices {

    private final BOMDetailsRepository bomDetailsRepository;


}
