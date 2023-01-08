package com.uniyaz.filmcategory.domain;

import com.uniyaz.category.domain.Category;
import com.uniyaz.film.domain.Film;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "film_category")
@Audited
public class FilmCategory implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    @ForeignKey(name = "fk_film_category_film")
    private Film film;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ForeignKey(name = "fk_film_category_category")
    private Category category;
    @Column(name = "last_update")
    private Date lastUpdate;

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

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

    @Override
    public String toString() {
        return "FilmCategory{" +
                "film=" + film +
                ", category=" + category +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategory that = (FilmCategory) o;
        return Objects.equals(film, that.film) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, category);
    }
}
