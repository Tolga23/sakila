package com.uniyaz.filmcategory.service;

import com.uniyaz.filmcategory.domain.FilmCategory;
import com.uniyaz.filmcategory.queryfilterdto.FilmCategoryQueryFilterDto;
import org.junit.Test;

import java.util.List;

public class FilmCategoryTest {

    @Test
    public void find() {
        FilmCategoryService filmCategoryService = new FilmCategoryService();
        List<FilmCategory> filmCategoryList = filmCategoryService.findAll();

        for (FilmCategory filmCategory : filmCategoryList) {
            System.out.println(filmCategory);
        }
    }

    @Test
    public void findAllByQueryfilterDto() {
        FilmCategoryService filmCategoryService = new FilmCategoryService();
        FilmCategoryQueryFilterDto filmCategoryQueryFilterDto = new FilmCategoryQueryFilterDto();
        List<FilmCategory> filmCategoryList = filmCategoryService.findAllByQueryFilterDto(filmCategoryQueryFilterDto);

        for (FilmCategory filmCategory : filmCategoryList) {
            System.out.println(filmCategory);
        }
    }
}
