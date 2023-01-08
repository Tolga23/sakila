package com.uniyaz.address.service;

import com.uniyaz.address.dao.AddressDao;
import com.uniyaz.address.domain.Address;
import com.uniyaz.address.queryfilterdto.AddressQueryFilterDto;

import java.util.List;

public class AddressService {
    public List<Address> findAll(){
        AddressDao addressDao = new AddressDao();
        return addressDao.findAll();
    }

    public Address save(Address address){
        AddressDao addressDao = new AddressDao();
        return addressDao.save(address);
    }

    public void delete(Address address){
        AddressDao addressDao = new AddressDao();
        addressDao.delete(address);
    }

    public List<Address> findAllByQueryFilterDto(AddressQueryFilterDto addressQueryFilterDto) {
        AddressDao addressDao = new AddressDao();
        return addressDao.findAllByQueryFilterDto(addressQueryFilterDto);
    }

    public List<Address> findAllByQueryFilterDtoCriteria(AddressQueryFilterDto addressQueryFilterDto) {
        AddressDao addressDao = new AddressDao();
        return addressDao.findAllByQueryFilterDtoCriteria(addressQueryFilterDto);
    }

    }
