package com.uniyaz.filmcategory.queryfilterdto;

import com.uniyaz.category.domain.Category;
import com.uniyaz.film.domain.Film;
import com.uniyaz.language.domain.Language;

public class FilmCategoryQueryFilterDto {

    private Film film;
    private Category category;

    private Language language;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
