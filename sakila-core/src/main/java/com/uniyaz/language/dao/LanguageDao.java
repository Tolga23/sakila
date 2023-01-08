package com.uniyaz.language.dao;

import com.uniyaz.HibernateUtil;
import com.uniyaz.city.domain.City;
import com.uniyaz.language.domain.Language;
import com.uniyaz.language.queryfilterdto.LanguageQueryFilterDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class LanguageDao {

    public List<Language> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery("Select language From Language language");
        List<Language> languageList = query.list();
        return languageList;
    }

    public Language save(Language language) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        language = (Language) currentSession.merge(language);
        transaction.commit();
        return language;
    }

    public void delete(Language language) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(language);
        transaction.commit();
    }

    public List<Language> findAllByQueryFilterDto(LanguageQueryFilterDto languageQueryFilterDto) {
        String hql =
                "Select language " +
                "From Language language " +
                "where 1=1 ";

        if (languageQueryFilterDto.getId() != null) hql += " and language.id = :languageId";
        if (languageQueryFilterDto.getName() != null) hql += " and language.name = :name";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(hql);

        if (languageQueryFilterDto.getId() != null) query.setParameter("languageId", languageQueryFilterDto.getId());
        if (languageQueryFilterDto.getName() != null) query.setParameter("name", languageQueryFilterDto.getName());

        List<Language> languageList = query.list();
        return languageList;
    }

    public List<Language> findAllByQueryFilterDtoCriteria(LanguageQueryFilterDto languageQueryFilterDto) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        Criteria criteria = currentSession.createCriteria(Language.class);

        if (languageQueryFilterDto.getId() != null) criteria.add(Restrictions.eq("id", languageQueryFilterDto.getId()));
        if (languageQueryFilterDto.getName() != null) criteria.add(Restrictions.eq("name", languageQueryFilterDto.getName()));

        List<Language> languageList = criteria.list();
        return languageList;
    }

}
