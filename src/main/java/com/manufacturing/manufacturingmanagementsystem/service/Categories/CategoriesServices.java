package com.manufacturing.manufacturingmanagementsystem.service.Categories;

import com.manufacturing.manufacturingmanagementsystem.models.CategoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.CategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriesServices implements ICategoriesServices {

    private final CategoriesRepository categoriesRepository;

    @Override
    public void createCategory(String categoryName) {
        CategoriesEntity category = new CategoriesEntity();
        category.setCategoryName(categoryName);
        categoriesRepository.save(category);
    }

    @Override
    public void updateCategory(Long id, String categoryName) {

    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public void getAllCategories() {

    }
}

