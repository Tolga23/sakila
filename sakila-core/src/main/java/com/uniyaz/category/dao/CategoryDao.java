package com.uniyaz.category.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.category.domain.Category;
import com.uniyaz.category.queryfilterdto.CategoryQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CategoryDao {

    public List<Category> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select category From Category category");
        List<Category> categoryList = query.list();
        return categoryList;
    }

    public Category save(Category category) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        category = (Category) currentSession.merge(category);
        transaction.commit();
        return category;
    }

    public void delete(Category category) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(category);
        transaction.commit();
    }

    public List<Category> findAllByQueryFilterDto(CategoryQueryFilterDto categoryQueryFilterDto) {
        String hql =
                "Select category " +
                "From Category category " +
                "where 1=1 ";

        if (categoryQueryFilterDto.getId() != null) hql += " and category.id = :categoryId";
        if (categoryQueryFilterDto.getName() != null) hql += " and category.name = :name";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (categoryQueryFilterDto.getId() != null) query.setParameter("categoryId", categoryQueryFilterDto.getId());
        if (categoryQueryFilterDto.getName() != null) query.setParameter("name", categoryQueryFilterDto.getName());

        List<Category> categoryList = query.list();
        return categoryList;
    }

    public List<Category> findAllByQueryFilterDtoCriteria(CategoryQueryFilterDto categoryQueryFilterDto) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(Category.class);

        if (categoryQueryFilterDto.getId() != null) criteria.add(Restrictions.eq("id", categoryQueryFilterDto.getId()));
        if (categoryQueryFilterDto.getName() != null) criteria.add(Restrictions.eq("name", categoryQueryFilterDto.getName()));

        List<Category> categoryList = criteria.list();
        return categoryList;
    }

}
