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

    public List<FilmActor> findAllByQueryFilterDto(FilmActorQueryFilterDto filmActorQueryFilterDto) {

        String hql =
                "Select filmActor " +
                "From FilmActor filmActor " +
                "Left Join fetch filmActor.actor actor " +
                "Left Join fetch filmActor.film film " +
                "where 1=1 ";

        if (filmActorQueryFilterDto.getId() != null) {
            hql += " and filmActor.id = :filmActorId";
        }

        if (filmActorQueryFilterDto.getActor() != null) {
            hql += " and filmActor.actor = :actor ";
        }

        if (filmActorQueryFilterDto.getFilm() != null) {
            hql += " and filmActor.film = :film ";
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (filmActorQueryFilterDto.getId() != null) {
            query.setParameter("filmActorId", filmActorQueryFilterDto.getId());
        }

        if (filmActorQueryFilterDto.getActor() != null) {
            query.setParameter("actor", filmActorQueryFilterDto.getActor().getFirstName());
        }

        if (filmActorQueryFilterDto.getFilm() != null) {
            query.setParameter("film", filmActorQueryFilterDto.getFilm().getTitle());
        }

        List<FilmActor> filmActorList = query.list();
        return filmActorList;
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