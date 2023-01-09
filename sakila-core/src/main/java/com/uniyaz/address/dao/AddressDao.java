package com.uniyaz.address.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.address.domain.Address;
import com.uniyaz.address.queryfilterdto.AddressQueryFilterDto;
import com.uniyaz.common.domain.dao.BaseDao;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AddressDao extends BaseDao<Address> {

    public AddressDao() {
        super(Address.class);
    }


    public List<Address> findAllByQueryFilterDto(AddressQueryFilterDto addressQueryFilterDto) {

        String hql =
                "Select address " +
                        "From Address address " +
                        "where 1=1 ";

        if (addressQueryFilterDto.getId() != null) {
            hql += " and address.id = :addressId";
        }

        if (addressQueryFilterDto.getDistrict() != null) {
            hql += " and address.district = :district";
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (addressQueryFilterDto.getId() != null) {
            query.setParameter("addressId", addressQueryFilterDto.getId());
        }

        if (addressQueryFilterDto.getDistrict() != null) {
            query.setParameter("district", addressQueryFilterDto.getDistrict());
        }

        List<Address> addressList = query.list();
        return addressList;
    }


    public List<Address> findAllByQueryFilterDtoCriteria(AddressQueryFilterDto addressQueryFilterDto) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(Address.class);

        if (addressQueryFilterDto.getId() != null) {
            criteria.add(Restrictions.eq("id", addressQueryFilterDto.getId()));
        }

        if (addressQueryFilterDto.getDistrict() != null) {
            criteria.add(Restrictions.eq("district", addressQueryFilterDto.getDistrict()));
        }

        List<Address> addressList = criteria.list();
        return addressList;
    }
}
