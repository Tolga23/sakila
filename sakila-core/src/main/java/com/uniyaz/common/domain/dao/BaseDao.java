package com.uniyaz.common.domain.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.common.domain.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BaseDao <T extends BaseEntity> {

    private Class<T> entityClass;

    public BaseDao(Class<T> entity) {
        this.entityClass = entity;
    }

    public List<T> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(entityClass);
        List<T> entityList = criteria.list();
        return entityList;
    }
    
    public T save(T entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        entity = (T) currentSession.merge(entity);
        transaction.commit();
        return entity;
    }

    public void delete(T entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(entity);
        transaction.commit();
    }
}
