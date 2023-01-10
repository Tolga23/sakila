package com.uniyaz.actor.queryfilterdto;

import com.uniyaz.actor.domain.Actor;
import com.uniyaz.common.domain.dto.BaseQueryFilterDto;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class ActorQueryFilterDto extends BaseQueryFilterDto{

    private Long id;
    private String firstName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Override
    public Query addQuery(Query query) {
        String hql = "";

        if (getId() != null) hql += " and actor.id = :id";
        if (getFirstName() != null) hql += " and actor.firstName = :firstName";

    if (getId() != null)  query.setParameter("id",getId());
    if (getFirstName() != null) query.setParameter("firstName",getFirstName());

        return query;
    }

    @Override
    public void addFilter(Criteria criteria) {

        Criterion idCriteria = Restrictions.eq("id",getId());
        Criterion nameCriteria = Restrictions.eq("firstName",getFirstName());
        criteria.add(Restrictions.or(idCriteria,nameCriteria));

    }
}