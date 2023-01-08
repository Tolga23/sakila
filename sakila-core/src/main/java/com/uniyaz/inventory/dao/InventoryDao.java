package com.uniyaz.inventory.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.inventory.domain.Inventory;
import com.uniyaz.inventory.queryfilterdto.InventoryQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.List;

public class InventoryDao {

    public List<Inventory> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select inventory From Inventory inventory");
        List<Inventory> inventoryList = query.list();
        return inventoryList;
    }

    public Inventory save(Inventory inventory) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        inventory = (Inventory) currentSession.merge(inventory);
        transaction.commit();
        return inventory;
    }

    public void delete(Inventory inventory) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(inventory);
        transaction.commit();
    }

    public List<Inventory> findAllByQueryFilterDto(InventoryQueryFilterDto inventoryQueryFilterDto) {
        String hql =
                "Select inventory " +
                        "From Inventory inventory " +
                        "Left Join fetch inventory.film film " +
                        "Left Join fetch inventory.store store " +
                        "where 1=1 ";

        if (inventoryQueryFilterDto.getId() != null) hql += " and inventory.id = :inventoryId";
        if (inventoryQueryFilterDto.getFilm() != null) hql += " and inventory.film = :film";
        if (inventoryQueryFilterDto.getStore() != null) hql += " and inventory.store = :store";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (inventoryQueryFilterDto.getId() != null) query.setParameter("inventoryId", inventoryQueryFilterDto.getId());

        if (inventoryQueryFilterDto.getFilm() != null) query.setParameter("film", inventoryQueryFilterDto.getFilm().getTitle());

        if (inventoryQueryFilterDto.getStore() != null) query.setParameter("store", inventoryQueryFilterDto.getStore());

        List<Inventory> inventoryList = query.list();
        return inventoryList;
    }

    public List<Inventory> findAllByQueryFilterDtoCriteria(InventoryQueryFilterDto inventoryQueryFilterDto) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(Inventory.class);
        criteria.createAlias("film", "film", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("store", "store", JoinType.LEFT_OUTER_JOIN);

        if (inventoryQueryFilterDto.getId() != null)
            criteria.add(Restrictions.eq("id", inventoryQueryFilterDto.getId()));
        if (inventoryQueryFilterDto.getFilm() != null)
            criteria.add(Restrictions.eq("film", inventoryQueryFilterDto.getFilm()));
        if (inventoryQueryFilterDto.getStore() != null)
            criteria.add(Restrictions.eq("store", inventoryQueryFilterDto.getStore()));

        List<Inventory> inventoryList = criteria.list();
        return inventoryList;
    }

}
