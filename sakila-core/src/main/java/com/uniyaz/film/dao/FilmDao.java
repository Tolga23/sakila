package com.uniyaz.film.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.film.domain.Film;
import com.uniyaz.film.queryfilterdto.FilmQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.List;

public class FilmDao {

    public List<Film> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select film From Film film");
        List<Film> filmList = query.list();
        return filmList;
    }

    public Film save(Film film) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        film = (Film) currentSession.merge(film);
        transaction.commit();
        return film;
    }

    public void delete(Film film) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(film);
        transaction.commit();
    }

    public List<Film> findAllByQueryFilterDto(FilmQueryFilterDto filmQueryFilterDto) {

        String hql =
                "Select film " +
                "From Film film " +
                "Left Join fetch film.language language " +
                "where 1=1 ";

        if (filmQueryFilterDto.getId() != null) {
            hql += " and film.id = :filmId";
        }

        if (filmQueryFilterDto.getTitle() != null) {
            hql += " and film.title = :title";
        }

        if (filmQueryFilterDto.getLanguageId() != null) {
            hql += " and film.language = :language ";
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (filmQueryFilterDto.getId() != null) {
            query.setParameter("filmId", filmQueryFilterDto.getId());
        }

        if (filmQueryFilterDto.getTitle() != null) {
            query.setParameter("title", filmQueryFilterDto.getTitle());
        }

        if (filmQueryFilterDto.getLanguageId() != null) {
            query.setParameter("language", filmQueryFilterDto.getLanguageId());
        }

        List<Film> filmList = query.list();
        return filmList;
    }

    public List<Film> findAllByQueryFilterDtoCriteria(FilmQueryFilterDto filmQueryFilterDto){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(Film.class);
        criteria.createAlias("language", "language", JoinType.LEFT_OUTER_JOIN);
        if (filmQueryFilterDto.getId() != null) {
            criteria.add(Restrictions.eq("id", filmQueryFilterDto.getId()));
        }
        if (filmQueryFilterDto.getTitle() != null) {
            criteria.add(Restrictions.eq("title", filmQueryFilterDto.getTitle()));
        }
        if (filmQueryFilterDto.getLanguageId() != null) {
            criteria.add(Restrictions.eq("language", filmQueryFilterDto.getLanguageId()));
        }
        List<Film> filmList = criteria.list();
        return filmList;
    }


    public List<Film> findFilmActorLanguage(FilmQueryFilterDto filmQueryFilterDto) {

        String hql =
                "Select film " +
                        "From Film film " +
                        "Left Join fetch film.language language " +
                        "Left Join fetch FilmActor.id id " +
                        "Left Join fetch Actor.actor actor " +
                        "where 1=1 ";

        if (filmQueryFilterDto.getId() != null) {
            hql += " and film.id = :filmId";
        }

        if (filmQueryFilterDto.getTitle() != null) {
            hql += " and film.title = :title";
        }

        if (filmQueryFilterDto.getLanguageId() != null) {
            hql += " and film.language = :language ";
        }

        if (filmQueryFilterDto.getActorName() != null) {
            hql += " and Actor.actor = :actor ";
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (filmQueryFilterDto.getId() != null) {
            query.setParameter("filmId", filmQueryFilterDto.getId());
        }

        if (filmQueryFilterDto.getTitle() != null) {
            query.setParameter("title", filmQueryFilterDto.getTitle());
        }

        if (filmQueryFilterDto.getLanguageId() != null) {
            query.setParameter("language", filmQueryFilterDto.getLanguageId());
        }

        if (filmQueryFilterDto.getActorName() != null) {
            query.setParameter("actor", filmQueryFilterDto.getActorName());
        }

        List<Film> filmList = query.list();
        return filmList;
    }
}