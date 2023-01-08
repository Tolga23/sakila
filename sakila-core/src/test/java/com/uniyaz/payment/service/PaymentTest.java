package com.uniyaz.payment.service;

import com.uniyaz.payment.domain.Payment;
import org.junit.Test;

import java.util.List;

public class PaymentTest {
    @Test
    public void testFindAll() {
        PaymentService paymentService = new PaymentService();
        List<Payment> paymentList = paymentService.findAll();

        for (Payment payment : paymentList) {
            System.out.println(payment);
        }
    }
}
