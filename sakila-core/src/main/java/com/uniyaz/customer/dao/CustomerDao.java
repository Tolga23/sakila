package com.uniyaz.customer.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.customer.domain.Customer;
import com.uniyaz.customer.queryfilterdto.CustomerQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.List;

public class CustomerDao {
    public List<Customer> findAll(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select customer From Customer customer");
        List<Customer> customerList = query.list();
        return customerList;
    }
    public Customer save(Customer customer) {
        SessionFactory sessionFcustomery = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFcustomery.openSession();
        Transaction transaction = currentSession.beginTransaction();
        customer = (Customer) currentSession.merge(customer);
        transaction.commit();
        return customer;
    }

    public void delete(Customer customer) {
        SessionFactory sessionFcustomery = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFcustomery.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(customer);
        transaction.commit();
    }

    public List<Customer> findAllByQueryFilterDto(CustomerQueryFilterDto customerQueryFilterDto) {

        String hql =
                "Select customer " +
                        "From Customer customer " +
                        "left join fetch customer.addressId addressId " +
                        "left join fetch customer.storeId storeId " +
                        "where 1=1 ";

        if (customerQueryFilterDto.getId() != null) {
            hql += " and customer.id = :customerId";
        }

        if (customerQueryFilterDto.getFirstName() != null) {
            hql += " and customer.firstName = :firstName";
        }

        if (customerQueryFilterDto.getAddressId() != null) {
            hql += " and customer.addressId = :addressId";
        }

        if (customerQueryFilterDto.getStoreId() != null) {
            hql += " and customer.storeId = :storeId";
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (customerQueryFilterDto.getId() != null) {
            query.setParameter("customerId", customerQueryFilterDto.getId());
        }

        if (customerQueryFilterDto.getFirstName() != null) {
            query.setParameter("firstName", customerQueryFilterDto.getFirstName());
        }

        if (customerQueryFilterDto.getAddressId() != null) {
            query.setParameter("addressId", customerQueryFilterDto.getAddressId());
        }

        if (customerQueryFilterDto.getStoreId() != null) {
            query.setParameter("storeId", customerQueryFilterDto.getStoreId());
        }

        List<Customer> customerList = query.list();
        return customerList;
    }


    public List<Customer> findAllByQueryFilterDtoCriteria(CustomerQueryFilterDto customerQueryFilterDto) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(Customer.class);
        criteria.createAlias("addressId", "addressId", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("storeId", "storeId", JoinType.LEFT_OUTER_JOIN);

        if (customerQueryFilterDto.getId() != null) {
            criteria.add(Restrictions.eq("id", customerQueryFilterDto.getId()));
        }

        if (customerQueryFilterDto.getFirstName() != null) {
            criteria.add(Restrictions.eq("firstName", customerQueryFilterDto.getFirstName()));
        }

        if (customerQueryFilterDto.getAddressId() != null) {
            criteria.add(Restrictions.eq("addressId.addressId", customerQueryFilterDto.getAddressId()));
        }

        if (customerQueryFilterDto.getStoreId() != null) {
            criteria.add(Restrictions.eq("storeId.storeId", customerQueryFilterDto.getStoreId()));
        }

        List<Customer> customerList = criteria.list();
        return customerList;
    }
}
