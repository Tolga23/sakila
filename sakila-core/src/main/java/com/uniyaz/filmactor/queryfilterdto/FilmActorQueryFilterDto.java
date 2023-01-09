package com.uniyaz.filmactor.queryfilterdto;

import com.uniyaz.actor.domain.Actor;
import com.uniyaz.film.domain.Film;

public class FilmActorQueryFilterDto {

    private Actor actor;
    private Film film;


    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
