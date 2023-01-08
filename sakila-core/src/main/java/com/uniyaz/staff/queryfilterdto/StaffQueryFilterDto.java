package com.uniyaz.staff.queryfilterdto;

import com.uniyaz.address.domain.Address;
import com.uniyaz.store.domain.Store;

public class StaffQueryFilterDto {

    private Long id;
    private String firstName;
    private Store storeId;
    private Address addressId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Store getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }
}
