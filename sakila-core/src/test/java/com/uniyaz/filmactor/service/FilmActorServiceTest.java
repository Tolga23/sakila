package com.uniyaz.filmactor.service;

import com.uniyaz.filmactor.dao.FilmActorDao;
import com.uniyaz.filmactor.domain.FilmActor;
import com.uniyaz.filmactor.queryfilterdto.FilmActorQueryFilterDto;
import org.junit.Test;

import java.util.List;

public class FilmActorServiceTest {

    @Test
    public void findAllTest() {

        FilmActorService filmActorService = new FilmActorService();
        FilmActorDao filmActorDao = new FilmActorDao();
        FilmActorQueryFilterDto dto = new FilmActorQueryFilterDto();

        List<FilmActor> filmActorList = filmActorDao.findAllByQueryFilterDtoCriteria(dto);
        for (FilmActor filmActor : filmActorList) {
            System.out.println(filmActor);
        }
    }
}