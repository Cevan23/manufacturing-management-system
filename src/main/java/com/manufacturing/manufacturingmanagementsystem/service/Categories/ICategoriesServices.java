package com.manufacturing.manufacturingmanagementsystem.service.Categories;

public interface ICategoriesServices {
    void createCategory(String categoryName);

    void updateCategory(Long id, String categoryName);

    void deleteCategory(Long id);

    void getAllCategories();
}
