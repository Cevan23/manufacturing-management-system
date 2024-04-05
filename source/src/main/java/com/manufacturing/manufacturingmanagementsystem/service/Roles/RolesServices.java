package com.manufacturing.manufacturingmanagementsystem.service.Roles;

import com.manufacturing.manufacturingmanagementsystem.repositories.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RolesServices implements IRolesServices {

    private final RolesRepository rolesRepository;

    // Các phương thức service khác cần thiết
}

