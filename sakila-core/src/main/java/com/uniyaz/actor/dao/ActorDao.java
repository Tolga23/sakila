package com.uniyaz.actor.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.actor.domain.Actor;
import com.uniyaz.actor.queryfilterdto.ActorQueryFilterDto;
import com.uniyaz.common.domain.dao.BaseDao;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ActorDao extends BaseDao<Actor>{

    public ActorDao() {
        super(Actor.class);
    }


    public List<Actor> findAllByName(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select actor From Actor actor where actor.firstName = :name ");
        query.setParameter("name", name);
        List<Actor> actorList = query.list();
        return actorList;
    }

    public List<Actor> findAllByQueryFilterDto(ActorQueryFilterDto actorQueryFilterDto) {

        String hql =
                "Select actor " +
                "From Actor actor " +
                "where 1=1 ";

        if (actorQueryFilterDto.getId() != null) {
            hql += " and actor.id = :actorId";
        }

        if (actorQueryFilterDto.getFirstName() != null) {
            hql += " and actor.firstName = :firstName";
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (actorQueryFilterDto.getId() != null) {
            query.setParameter("actorId", actorQueryFilterDto.getId());
        }

        if (actorQueryFilterDto.getFirstName() != null) {
            query.setParameter("firstName", actorQueryFilterDto.getFirstName());
        }

        List<Actor> actorList = query.list();
        return actorList;
    }


    public List<Actor> findAllByQueryFilterDtoCriteria(ActorQueryFilterDto actorQueryFilterDto) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(Actor.class);

        if (actorQueryFilterDto.getId() != null) {
            criteria.add(Restrictions.eq("id", actorQueryFilterDto.getId()));
        }

        if (actorQueryFilterDto.getFirstName() != null) {
            criteria.add(Restrictions.eq("firstName", actorQueryFilterDto.getFirstName()));
        }

        List<Actor> actorList = criteria.list();
        return actorList;
    }
}