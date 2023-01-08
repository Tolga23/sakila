package com.uniyaz.staff.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.staff.queryfilterdto.StaffQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import com.uniyaz.staff.domain.Staff;

import java.util.List;

public class StaffDao {

    public List<Staff> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select staff From Staff staff");
        List<Staff> staffList = query.list();
        return staffList;
    }

    public Staff save(Staff staff) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        staff = (Staff) currentSession.merge(staff);
        transaction.commit();
        return staff;
    }

    public void delete(Staff staff) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(staff);
        transaction.commit();
    }

    public List<Staff> findAllByQueryFilterDto(StaffQueryFilterDto staffQueryFilterDto) {

        String hql = "Select staff " +
                "From Staff staff " +
                "Left Join fetch staff.address address " +
                "Left Join fetch staff.store store " +
                "where 1=1 ";

        if (staffQueryFilterDto.getId() != null) {
            hql += " and staff.id = :staffId";
        }

        if (staffQueryFilterDto.getFirstName() != null) {
            hql += " and staff.firstName = :firstName";
        }

        if (staffQueryFilterDto.getAddressId() != null) {
            hql += " and staff.addressId = :address ";
        }

        if (staffQueryFilterDto.getStoreId() != null) {
            hql += " and staff.storeId = :store ";
        }


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (staffQueryFilterDto.getId() != null) {
            query.setParameter("staffId", staffQueryFilterDto.getId());
        }

        if (staffQueryFilterDto.getFirstName() != null) {
            query.setParameter("firstName", staffQueryFilterDto.getFirstName());
        }

        if (staffQueryFilterDto.getAddressId() != null) {
            query.setParameter("address", staffQueryFilterDto.getAddressId());
        }

        if (staffQueryFilterDto.getStoreId() != null) {
            query.setParameter("store", staffQueryFilterDto.getStoreId());
        }


        List<Staff> staffList = query.list();
        return staffList;
    }

    public List<Staff> findAllByQueryFilterDtoCriteria(StaffQueryFilterDto staffQueryFilterDto) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(Staff.class);
        criteria.createAlias("addressId", "address", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("storeId", "store", JoinType.LEFT_OUTER_JOIN);

        if (staffQueryFilterDto.getId() != null) {
            criteria.add(Restrictions.eq("id", staffQueryFilterDto.getId()));
        }

        if (staffQueryFilterDto.getFirstName() != null) {
            criteria.add(Restrictions.eq("firstName", staffQueryFilterDto.getFirstName()));
        }

        if (staffQueryFilterDto.getAddressId() != null) {
            criteria.add(Restrictions.eq("address.addressId", staffQueryFilterDto.getAddressId().getAdress()));
        }

        if (staffQueryFilterDto.getStoreId() != null) {
            criteria.add(Restrictions.eq("store.storeId", staffQueryFilterDto.getStoreId()));
        }


        List<Staff> staffList = criteria.list();
        return staffList;
    }


}
