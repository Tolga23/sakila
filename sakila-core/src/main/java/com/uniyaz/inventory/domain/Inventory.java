package com.uniyaz.inventory.domain;

import com.uniyaz.common.domain.BaseEntity;
import com.uniyaz.film.domain.Film;
import com.uniyaz.store.domain.Store;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory")
@Audited
public class Inventory extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inventory_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    @ForeignKey(name = "fk_inventory_film")
    private Film filmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @ForeignKey(name = "fk_inventory_store")
    private Store storeId;
    
    @Column(name = "last_update")
    private Date lastUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getFilmId() {
        return filmId;
    }

    public void setFilmId(Film filmId) {
        this.filmId = filmId;
    }

    public Object getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", filmId=" + filmId +
                ", storeId=" + storeId +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
