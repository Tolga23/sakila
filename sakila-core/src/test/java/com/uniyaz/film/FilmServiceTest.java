package com.uniyaz.film;

import com.uniyaz.film.domain.Film;
import com.uniyaz.film.service.FilmService;
import org.junit.Test;

import java.util.List;

public class FilmServiceTest {

    @Test
    public void find() {
        FilmService filmService = new FilmService();
        List<Film> filmList = filmService.findAll();

        for (Film film : filmList) {
            System.out.println(film);
        }
    }
}
