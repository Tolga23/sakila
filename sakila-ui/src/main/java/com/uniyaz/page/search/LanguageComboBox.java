package com.uniyaz.page.search;

import com.uniyaz.language.domain.Language;
import com.uniyaz.language.service.LanguageService;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;

import java.util.List;

public class LanguageComboBox extends ComboBox {

    public LanguageComboBox() {
        LanguageService languageService = new LanguageService();
        List<Language> languageList = languageService.findAll();
        for (Language language : languageList) {
            Item item = addItem(language);
            setItemCaption(item, language.getName());
        }
    }

}
