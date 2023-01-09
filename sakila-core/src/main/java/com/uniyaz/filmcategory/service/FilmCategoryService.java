package com.uniyaz.filmcategory.service;

import com.uniyaz.filmcategory.dao.FilmCategoryDao;
import com.uniyaz.filmcategory.domain.FilmCategory;
import com.uniyaz.filmcategory.queryfilterdto.FilmCategoryQueryFilterDto;

import java.util.List;

public class FilmCategoryService {

    public List<FilmCategory> findAll() {
        FilmCategoryDao filmCategoryDao = new FilmCategoryDao();
        return filmCategoryDao.findAll();
    }

    public FilmCategory save(FilmCategory filmCategory) {
        FilmCategoryDao filmCategoryDao = new FilmCategoryDao();
        return filmCategoryDao.save(filmCategory);
    }

    public void delete(FilmCategory filmCategory) {
        FilmCategoryDao filmCategoryDao = new FilmCategoryDao();
        filmCategoryDao.delete(filmCategory);
    }

    public List<FilmCategory> findAllByQueryFilterDto(FilmCategoryQueryFilterDto filmCategoryQueryFilterDto) {
        FilmCategoryDao filmCategoryDao = new FilmCategoryDao();
        return filmCategoryDao.findAllByQueryFilterDto(filmCategoryQueryFilterDto);
    }

    public List<FilmCategory> findAllByQueryFilterDtoCriteria(FilmCategoryQueryFilterDto filmCategoryQueryFilterDto) {
        FilmCategoryDao filmCategoryDao = new FilmCategoryDao();
        return filmCategoryDao.findAllByQueryFilterDtoCriteria(filmCategoryQueryFilterDto);
    }

    public List<FilmCategory> findAllByQueryFilterDtoDetachedCriteria(FilmCategoryQueryFilterDto filmCategoryQueryFilterDto){
        FilmCategoryDao filmCategoryDao = new FilmCategoryDao();
        return filmCategoryDao.findAllByQueryFilterDtoDetachedCriteria(filmCategoryQueryFilterDto);
    }

}
