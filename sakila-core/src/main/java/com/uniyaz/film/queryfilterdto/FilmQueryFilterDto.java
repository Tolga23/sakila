package com.uniyaz.film.queryfilterdto;

import com.uniyaz.language.domain.Language;

public class FilmQueryFilterDto {

    private Long id;
    private String title;
    private Language languageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }
}
