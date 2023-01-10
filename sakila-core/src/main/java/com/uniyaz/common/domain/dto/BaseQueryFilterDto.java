package com.uniyaz.common.domain.dto;

import com.uniyaz.common.domain.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;

public abstract class BaseQueryFilterDto {

    public abstract Query addQuery(Query query);
    public abstract void addFilter(Criteria criteria);
}
