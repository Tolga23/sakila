package com.uniyaz.common.domain.dto;

import com.uniyaz.common.domain.BaseEntity;
import org.hibernate.Criteria;

public abstract class BaseQueryFilterDto<T extends BaseEntity> {

    public abstract Criteria addFilter(Criteria criteria);
}
