package com.uniyaz.filmtext.service;

import com.uniyaz.filmtext.dao.FilmTextDao;
import com.uniyaz.filmtext.domain.FilmText;
import com.uniyaz.filmtext.queryfilterdto.FilmTextQueryFilterDto;

import java.util.List;

public class FilmTextService {

    public List<FilmText> findAll() {
        FilmTextDao filmTextDao = new FilmTextDao();
        return filmTextDao.findAll();
    }

    public FilmText save(FilmText filmText) {
        FilmTextDao filmTextDao = new FilmTextDao();
        return filmTextDao.save(filmText);
    }

    public void delete(FilmText filmText) {
        FilmTextDao filmTextDao = new FilmTextDao();
        filmTextDao.delete(filmText);
    }

    public List<FilmText> findAllByQueryFilterDto(FilmTextQueryFilterDto filmTextQueryFilterDto) {
        FilmTextDao filmTextDao = new FilmTextDao();
        return filmTextDao.findAllByQueryFilterDto(filmTextQueryFilterDto);
    }

    public List<FilmText> findAllByQueryFilterDtoCriteria(FilmTextQueryFilterDto filmTextQueryFilterDto) {
        FilmTextDao filmTextDao = new FilmTextDao();
        return filmTextDao.findAllByQueryFilterDtoCriteria(filmTextQueryFilterDto);
    }

}
