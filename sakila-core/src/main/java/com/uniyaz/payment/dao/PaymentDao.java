package com.uniyaz.payment.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.payment.domain.Payment;
import com.uniyaz.payment.queryfilterdto.PaymentQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.List;

public class PaymentDao {

    public List<Payment> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("Select payment From Payment payment left join fetch payment.customer left join fetch payment.staff left join fetch payment.rental where 1=1");
        List<Payment> paymentList = query.list();
        return paymentList;
    }

    public Payment save(Payment payment) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        payment = (Payment) session.merge(payment);
        transaction.commit();
        return payment;
    }

    public void delete(Payment payment) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(payment);
        transaction.commit();
    }

    public List<Payment> findAllByQueryFilterDto(PaymentQueryFilterDto paymentQueryFilterDto) {
        String hql =
                "Select payment " +
                        "From Payment payment " +
                        "Left Join fetch payment.customer customer " +
                        "Left Join fetch payment.staff staff " +
                        "Left Join fetch payment.rental rental " +
                        "where 1=1 ";

        if (paymentQueryFilterDto.getId() != null) hql += " and payment.id = :paymentId";
        if (paymentQueryFilterDto.getCustomer() != null) hql += " and payment.customer = :customer ";
        if (paymentQueryFilterDto.getStaff() != null) hql += " and payment.staff = :staff ";
        if (paymentQueryFilterDto.getRental() != null) hql += " and payment.rental = :rental ";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);

        if (paymentQueryFilterDto.getId() != null) query.setParameter("paymentId", paymentQueryFilterDto.getId());
        if (paymentQueryFilterDto.getCustomer() != null) query.setParameter("customer", paymentQueryFilterDto.getCustomer());
        if (paymentQueryFilterDto.getStaff() != null) query.setParameter("staff", paymentQueryFilterDto.getStaff());
        if (paymentQueryFilterDto.getRental() != null) query.setParameter("rental", paymentQueryFilterDto.getRental());

        List<Payment> paymentList = query.list();
        return paymentList;
    }

    public List<Payment> findAllByQueryFilterDtoCriteria(PaymentQueryFilterDto paymentQueryFilterDto) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Payment.class);
        criteria.createAlias("customer", "customer", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("staff", "staff", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("rental", "rental", JoinType.LEFT_OUTER_JOIN);

        if (paymentQueryFilterDto.getId() != null) criteria.add(Restrictions.eq("id", paymentQueryFilterDto.getId()));
        if (paymentQueryFilterDto.getCustomer() != null) criteria.add(Restrictions.eq("customer", paymentQueryFilterDto.getCustomer()));
        if (paymentQueryFilterDto.getStaff() != null) criteria.add(Restrictions.eq("staff", paymentQueryFilterDto.getStaff()));
        if (paymentQueryFilterDto.getRental() != null) criteria.add(Restrictions.eq("rental", paymentQueryFilterDto.getRental()));

        List<Payment> paymentList = criteria.list();
        return paymentList;
    }
}
