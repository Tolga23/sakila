package com.uniyaz.address.service;

import com.uniyaz.address.domain.Address;
import com.uniyaz.address.queryfilterdto.AddressQueryFilterDto;
import com.uniyaz.city.domain.City;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class AddressServiceTest {
    @Test
    public void find() {
        AddressService addressService = new AddressService();
        List<Address> addressList = addressService.findAll();

        for (Address address : addressList) {
            System.out.println(address);
        }

    }

    @Test
    public void saveAdressTest() {
        Address address = new Address();
        City city = new City();

        address.setId(605l);
        city.setId(5L);
        address.setCity(city);
        address.setAdress("Test");
        address.setDistrict("Dist Test");
        address.setPostalCode("3333");
        address.setPhone("1111111");
        address.setLastUpdate(new Date());

        AddressService addressService = new AddressService();
        addressService.save(address);

        System.out.println(address);
    }

    @Test
    public void deleteTest() {
        Address address = new Address();
        address.setId(605l);

        AddressService addressService = new AddressService();
        addressService.delete(address);
    }

    @Test
    public void dtoSearchTest() {
        AddressQueryFilterDto addressQueryFilterDto = new AddressQueryFilterDto();
        addressQueryFilterDto.setId(2l);
        
        AddressService addressService = new AddressService();
        addressService.findAllByQueryFilterDto(addressQueryFilterDto);

        System.out.println(addressQueryFilterDto);
    }
}
