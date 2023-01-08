package com.uniyaz.country.service;

import com.uniyaz.country.domain.Country;
import org.junit.Test;

import java.util.List;

public class CountryTest {

    @Test
    public void testFindAll() {
        CountryService countryService = new CountryService();
        List<Country> countryList = countryService.findAll();

        for (Country country : countryList) {
            System.out.println(country);
        }
    }
}
