package com.uniyaz.rental.domain;

import com.uniyaz.common.domain.BaseEntity;
import com.uniyaz.customer.domain.Customer;
import com.uniyaz.inventory.domain.Inventory;
import com.uniyaz.staff.domain.Staff;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rental")
@Audited
public class Rental extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rental_id")
    private Long id;

    @Column(name = "rental_date")
    private Date rentalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    @ForeignKey(name = "fk_rental_inventory")
    private Inventory inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @ForeignKey(name = "fk_rental_customer")
    private Customer customer;

    @Column(name = "return_date")
    private Date returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    @ForeignKey(name = "fk_rental_staff")
    private Staff staff;

    @Column(name = "last_update")
    private Date lastUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Object getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Object getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Object getStaffId() {
        return staff;
    }

    public void setStaffId(Staff staffId) {
        this.staff = staffId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", rentalDate=" + rentalDate +
                ", inventory=" + inventory +
                ", customer=" + customer +
                ", returnDate=" + returnDate +
                ", staff=" + staff +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
