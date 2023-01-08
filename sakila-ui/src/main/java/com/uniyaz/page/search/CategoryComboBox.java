package com.uniyaz.page.search;

import com.uniyaz.category.domain.Category;
import com.uniyaz.category.service.CategoryService;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;

import java.util.List;

public class CategoryComboBox extends ComboBox {

    public CategoryComboBox() {
        CategoryService categoryService = new CategoryService();
        List<Category> categoryList = categoryService.findAll();
        for (Category category : categoryList) {
            Item item = addItem(category);
            setItemCaption(item, category.getName());
        }
    }


}
