package com.uniyaz.filmactor.queryfilterdto;

import com.uniyaz.actor.domain.Actor;
import com.uniyaz.film.domain.Film;

public class FilmActorQueryFilterDto {

    private Long id;
    private Actor actor;
    private Film film;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
