package com.uniyaz.common.domain.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.common.domain.BaseEntity;
import com.uniyaz.common.domain.dto.BaseQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

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

    public List<T> findAllByQueryFilterDto(BaseQueryFilterDto baseQueryFilterDto) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(entityClass);

        if (baseQueryFilterDto != null){
            baseQueryFilterDto.addFilter(criteria);
        }

        return criteria.list();
    }

    public List<T> findAllByQueryFilterDtoHql(BaseQueryFilterDto baseQueryFilterDto){

        String hql = "select entity from " + entityClass.getSimpleName() + " entity where 1=1";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (baseQueryFilterDto != null){
            query = baseQueryFilterDto.addQuery(query);
        }

        List list = query.list();

        return list;

    }

}
