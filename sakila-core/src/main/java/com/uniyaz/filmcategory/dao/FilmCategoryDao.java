package com.uniyaz.filmcategory.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.filmcategory.domain.FilmCategory;
import com.uniyaz.filmcategory.queryfilterdto.FilmCategoryQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.List;

public class FilmCategoryDao {

    public List<FilmCategory> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("Select filmCategory From FilmCategory filmCategory");
        List<FilmCategory> filmCategoryList = query.list();
        return filmCategoryList;
    }

    public FilmCategory save(FilmCategory filmCategory) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        filmCategory = (FilmCategory) session.merge(filmCategory);
        transaction.commit();
        return filmCategory;
    }

    public void delete(FilmCategory filmCategory) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(filmCategory);
        transaction.commit();
    }

    public List<FilmCategory> findAllByQueryFilterDto(FilmCategoryQueryFilterDto filmCategoryQueryFilterDto) {
        String hql =
                 "SELECT fc FROM FilmCategory fc"
                + " Left JOIN fetch fc.category c"
                + " Left JOIN fetch  fc.film f"
                + " Left JOIN fetch  f.language l"
                + " WHERE 1=1";

        if (filmCategoryQueryFilterDto.getCategory() != null) {
            hql += " and fc.category = :category ";
        }
        if (filmCategoryQueryFilterDto.getFilm() != null) {
            hql += " and fc.film = :film ";
        }
        if (filmCategoryQueryFilterDto.getLanguage() != null) {
            hql += " and f.language = :language ";
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);

        if (filmCategoryQueryFilterDto.getCategory() != null) {
            query.setParameter("category", filmCategoryQueryFilterDto.getCategory());
        }
        if (filmCategoryQueryFilterDto.getFilm() != null) {
            query.setParameter("film", filmCategoryQueryFilterDto.getFilm());
        }

        if (filmCategoryQueryFilterDto.getLanguage() != null) {
            query.setParameter("language", filmCategoryQueryFilterDto.getLanguage());
        }


        List<FilmCategory> filmCategoryList = query.list();
        return filmCategoryList;
    }

    public List<FilmCategory> findAllByQueryFilterDtoCriteria(FilmCategoryQueryFilterDto filmCategoryQueryFilterDto) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(FilmCategory.class);
        criteria.createAlias("category", "category", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("film", "film", JoinType.LEFT_OUTER_JOIN);

        if (filmCategoryQueryFilterDto.getCategory() != null) {
            criteria.add(Restrictions.eq("category", filmCategoryQueryFilterDto.getCategory()));
        }
        if (filmCategoryQueryFilterDto.getFilm() != null) {
            criteria.add(Restrictions.eq("film", filmCategoryQueryFilterDto.getFilm()));
        }
        List<FilmCategory> filmCategoryList = criteria.list();
        return filmCategoryList;
    }


}
