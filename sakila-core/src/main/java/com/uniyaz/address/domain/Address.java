package com.uniyaz.address.domain;

import com.uniyaz.city.domain.City;
import com.uniyaz.common.domain.BaseEntity;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "address")
@Audited
public class Address extends BaseEntity {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address",length = 50)
    private String adress;

    @Column(name = "district",length = 20)
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @ForeignKey(name = "fk_address_city")
    private City city;

    @Column(name = "postal_code",length = 10)
    private String postalCode;

    @Column(name = "phone",length = 20)
    private String phone;

    @Column(name = "last_update")
    private Date lastUpdate;

    @Override
    public Long getId() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", adress='" + adress + '\'' +
                ", district='" + district + '\'' +
                ", city=" + city +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
