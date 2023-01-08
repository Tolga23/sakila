package com.uniyaz.rental.service;

import com.uniyaz.rental.domain.Rental;
import org.junit.Test;

import java.util.List;

public class RentalTest {

    @Test
    public void testFindAll() {
        RentalService rentalService = new RentalService();
        List<Rental> rentalList = rentalService.findAll();

        for (Rental rental : rentalList) {
            System.out.println(rental);
        }
    }
}
