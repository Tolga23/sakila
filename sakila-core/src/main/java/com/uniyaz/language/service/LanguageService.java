package com.uniyaz.language.service;

import com.uniyaz.language.dao.LanguageDao;
import com.uniyaz.language.domain.Language;
import com.uniyaz.language.queryfilterdto.LanguageQueryFilterDto;

import java.util.List;

public class LanguageService {

    public List<Language> findAll() {
        LanguageDao languageDao = new LanguageDao();
        return languageDao.findAll();
    }

    public Language save(Language language) {
        LanguageDao languageDao = new LanguageDao();
        return languageDao.save(language);
    }

    public void delete(Language language) {
        LanguageDao languageDao = new LanguageDao();
        languageDao.delete(language);
    }

    public List<Language> findAllByQueryFilterDto(LanguageQueryFilterDto languageQueryFilterDto) {
        LanguageDao languageDao = new LanguageDao();
        return languageDao.findAllByQueryFilterDto(languageQueryFilterDto);
    }

    public List<Language> findAllByQueryFilterDtoCriteria(LanguageQueryFilterDto languageQueryFilterDto) {
        LanguageDao languageDao = new LanguageDao();
        return languageDao.findAllByQueryFilterDtoCriteria(languageQueryFilterDto);
    }


}
