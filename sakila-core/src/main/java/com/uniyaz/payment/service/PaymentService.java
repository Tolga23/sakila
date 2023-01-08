package com.uniyaz.payment.service;

import com.uniyaz.payment.dao.PaymentDao;
import com.uniyaz.payment.domain.Payment;
import com.uniyaz.payment.queryfilterdto.PaymentQueryFilterDto;

import java.util.List;

public class PaymentService {

    public List<Payment> findAll() {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.findAll();
    }

    public Payment save(Payment payment) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.save(payment);
    }

    public void delete(Payment payment) {
        PaymentDao paymentDao = new PaymentDao();
        paymentDao.delete(payment);
    }

    public List<Payment> findAllByQueryFilterDto(PaymentQueryFilterDto paymentQueryFilterDto) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.findAllByQueryFilterDto(paymentQueryFilterDto);
    }

    public List<Payment> findAllByQueryFilterDtoCriteria(PaymentQueryFilterDto paymentQueryFilterDto) {
        PaymentDao paymentDao = new PaymentDao();
        return paymentDao.findAllByQueryFilterDtoCriteria(paymentQueryFilterDto);
    }

}
