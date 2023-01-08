package com.uniyaz.category.service;

import com.uniyaz.category.domain.Category;
import org.junit.Test;

import java.util.List;

public class CategoryTest {

    @Test
    public void testFindAll() {
        CategoryService categoryService = new CategoryService();
        List<Category> categoryList = categoryService.findAll();

        for (Category category : categoryList) {
            System.out.println(category);
        }
    }

}
