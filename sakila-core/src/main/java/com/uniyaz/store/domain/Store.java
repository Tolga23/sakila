package com.uniyaz.store.domain;

import com.uniyaz.address.domain.Address;
import com.uniyaz.common.domain.BaseEntity;
import com.uniyaz.staff.domain.Staff;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "store")
@Audited
public class Store extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    @ForeignKey(name = "fk_store_staff")
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @ForeignKey(name = "fk_store_address")
    private Address address;
    
    @Column(name = "last_update")
    private Date lastUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getManagerStaffId() {
        return staff;
    }

    public void setManagerStaffId(Staff managerStaffId) {
        this.staff = managerStaffId;
    }

    public Object getAddressId() {
        return address;
    }

    public void setAddressId(Address addressId) {
        this.address = addressId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
