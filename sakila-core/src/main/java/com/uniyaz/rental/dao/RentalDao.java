package com.uniyaz.rental.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.rental.domain.Rental;
import com.uniyaz.rental.queryfilterdto.RentalQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.List;
public class RentalDao {

    public List<Rental> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select rental From Rental rental");
        List<Rental> rentalList = query.list();
        return rentalList;
    }

    public Rental save(Rental rental) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        rental = (Rental) currentSession.merge(rental);
        transaction.commit();
        return rental;
    }

    public void delete(Rental rental) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(rental);
        transaction.commit();
    }

    public List<Rental> findAllByQueryFilterDto(RentalQueryFilterDto rentalQueryFilterDto) {
        String hql =
                "Select rental " +
                        "From Rental rental " +
                        "Left Join fetch rental.customer customer " +
                        "Left Join fetch rental.staff staff " +
                        "Left Join fetch rental.inventory inventory " +
                        "where 1=1 ";

        if (rentalQueryFilterDto.getId() != null) hql += " and rental.id = :rentalId";
        if (rentalQueryFilterDto.getInventory() != null) hql += " and rental.inventory = :inventory ";
        if (rentalQueryFilterDto.getCustomer() != null) hql += " and rental.customer = :customer ";
        if (rentalQueryFilterDto.getStaff() != null) hql += " and rental.staff = :staff ";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (rentalQueryFilterDto.getId() != null) query.setParameter("rentalId", rentalQueryFilterDto.getId());
        if (rentalQueryFilterDto.getInventory() != null)
            query.setParameter("inventory", rentalQueryFilterDto.getInventory());
        if (rentalQueryFilterDto.getCustomer() != null)
            query.setParameter("customer", rentalQueryFilterDto.getCustomer());
        if (rentalQueryFilterDto.getStaff() != null) query.setParameter("staff", rentalQueryFilterDto.getStaff());

        List<Rental> rentalList = query.list();
        return rentalList;
    }

    public List<Rental> findAllByQueryFilterDtoCriteria(RentalQueryFilterDto rentalQueryFilterDto) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(Rental.class);
        criteria.createAlias("inventory", "inventory", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("customer", "customer", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("staff", "staff", JoinType.LEFT_OUTER_JOIN);

        if (rentalQueryFilterDto.getId() != null) criteria.add(Restrictions.eq("id", rentalQueryFilterDto.getId()));
        if (rentalQueryFilterDto.getInventory() != null) criteria.add(Restrictions.eq("inventory", rentalQueryFilterDto.getInventory()));
        if (rentalQueryFilterDto.getCustomer() != null) criteria.add(Restrictions.eq("customer", rentalQueryFilterDto.getCustomer()));
        if (rentalQueryFilterDto.getStaff() != null) criteria.add(Restrictions.eq("staff", rentalQueryFilterDto.getStaff()));

        List<Rental> rentalList = criteria.list();
        return rentalList;
    }

}
