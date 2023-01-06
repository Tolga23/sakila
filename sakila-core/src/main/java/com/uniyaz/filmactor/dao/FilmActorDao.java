package com.uniyaz.filmactor.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.filmactor.domain.FilmActor;
import com.uniyaz.filmactor.queryfilterdto.FilmActorQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class FilmActorDao {

    public List<FilmActor> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select filmActor From FilmActor filmActor");
        List<FilmActor> filmActorList = query.list();
        return filmActorList;
    }

    public FilmActor save(FilmActor filmActor) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        filmActor = (FilmActor) currentSession.merge(filmActor);
        transaction.commit();
        return filmActor;
    }

    public void delete(FilmActor filmActor) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(filmActor);
        transaction.commit();
    }

    public List<FilmActor> findAllByQueryFilterDtoCriteria(FilmActorQueryFilterDto filmActorQueryFilterDto) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(FilmActor.class);

        if (filmActorQueryFilterDto.getId() != null) {
            criteria.add(Restrictions.eq("id", filmActorQueryFilterDto.getId()));
        }

        List<FilmActor> filmActors = criteria.list();
        return filmActors;
    }
}