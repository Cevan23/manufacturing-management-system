package com.manufacturing.manufacturingmanagementsystem.service.Categories;

import com.manufacturing.manufacturingmanagementsystem.repositories.CategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriesServices implements ICategoriesServices {

    private final CategoriesRepository categoriesRepository;

    // Các phương thức service khác cần thiết
}

