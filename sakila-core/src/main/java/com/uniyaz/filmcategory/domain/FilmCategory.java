package com.uniyaz.filmcategory.domain;

import com.uniyaz.category.domain.Category;
import com.uniyaz.film.domain.Film;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "film_category")
@Audited
public class FilmCategory implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id")
    @JoinColumn(name = "film_id")
    @ForeignKey(name = "fk_film_category_film")
    private Film film;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id")
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

    @Override
    public String toString() {
        return "FilmCategory{" +
                "film=" + film +
                ", category=" + category +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
