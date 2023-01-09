package com.uniyaz.filmcategory.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.actor.domain.Actor;
import com.uniyaz.category.domain.Category;
import com.uniyaz.filmactor.domain.FilmActor;
import com.uniyaz.filmcategory.domain.FilmCategory;
import com.uniyaz.filmcategory.queryfilterdto.FilmCategoryQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

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
        if (filmCategoryQueryFilterDto.getFilmName() != null) {
            hql += " and fc.film.title = :film ";
        }
        if (filmCategoryQueryFilterDto.getLanguage() != null) {
            hql += " and f.language = :language ";
        }
        if (filmCategoryQueryFilterDto.getActorName() != null) {
            hql += " and fc.film.id in (select fa.film.id from FilmActor fa left join fa.actor actor where actor.firstName = :actorName )";
        }

        hql += " order by f.title desc ";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);

        if (filmCategoryQueryFilterDto.getCategory() != null) {
            query.setParameter("category", filmCategoryQueryFilterDto.getCategory());
        }
        if (filmCategoryQueryFilterDto.getFilmName() != null) {
            query.setParameter("film", filmCategoryQueryFilterDto.getFilm().getTitle());
        }

        if (filmCategoryQueryFilterDto.getLanguage() != null) {
            query.setParameter("language", filmCategoryQueryFilterDto.getLanguage());
        }

        if (filmCategoryQueryFilterDto.getActorName() != null) {
            query.setParameter("actorName", filmCategoryQueryFilterDto.getActorName());
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

    public List<FilmCategory> findAllByQueryFilterDtoDetachedCriteria(FilmCategoryQueryFilterDto filmCategoryQueryFilterDto) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(FilmCategory.class,"filmCategory");
        criteria.createAlias("filmCategory.film", "film", JoinType.LEFT_OUTER_JOIN);

        if (filmCategoryQueryFilterDto.getCategory() != null) {
            criteria.add(Restrictions.eq("filmCategory.category", filmCategoryQueryFilterDto.getCategory()));
        }

        if (filmCategoryQueryFilterDto.getFilmName() != null) {
            criteria.add(Restrictions.eq("film.title", filmCategoryQueryFilterDto.getFilmName()));
        }

        if (filmCategoryQueryFilterDto.getLanguage() != null) {
            criteria.add(Restrictions.eq("film.language", filmCategoryQueryFilterDto.getLanguage()));
        }

        if (filmCategoryQueryFilterDto.getActorName() != null) {

            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FilmActor.class, "filmActor");
            detachedCriteria.createAlias("filmActor.actor", "actor", JoinType.LEFT_OUTER_JOIN);
            detachedCriteria.add(Restrictions.eq("actor.firstName", filmCategoryQueryFilterDto.getActorName()));
            detachedCriteria.setProjection(Projections.property("filmActor.film.id"));

            criteria.add(Property.forName("film.id").in(detachedCriteria));
        }

        criteria
                .addOrder(Order.desc("film.title"))
                .addOrder(Order.desc("film.id"))
                .addOrder(Order.asc("film.lastUpdate"));

        List<FilmCategory> filmCategoryList = criteria.list();;
        return filmCategoryList;
    }


}
