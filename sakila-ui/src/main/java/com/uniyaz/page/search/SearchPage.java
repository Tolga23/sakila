package com.uniyaz.page.search;

import com.uniyaz.category.domain.Category;
import com.uniyaz.film.domain.Film;
import com.uniyaz.filmcategory.domain.FilmCategory;
import com.uniyaz.filmcategory.queryfilterdto.FilmCategoryQueryFilterDto;
import com.uniyaz.filmcategory.service.FilmCategoryService;
import com.uniyaz.language.domain.Language;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.util.Date;
import java.util.List;

public class SearchPage extends VerticalLayout {

    private HorizontalLayout buttonLayout;
    private FormLayout filterFormLayout;
    private VerticalLayout tableLayout;
    private FormLayout formLayout;

    private TextField filmFilter;
    private CategoryComboBox categoryFilter;
    private LanguageComboBox languageFilter;

    private TextField filmNameField;

    private CategoryComboBox categoryNameField;
    private LanguageComboBox languageField;


    private Table table;

    private Button saveButton;
    private Button deleteButton;
    private Button searchButton;

    public SearchPage() {

        setMargin(true);
        setSpacing(true);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(true);
        addComponent(horizontalLayout);

        VerticalLayout verticalLayoutSol = new VerticalLayout();
        horizontalLayout.addComponent(verticalLayoutSol);

        buildFilterFormLayout();
        verticalLayoutSol.addComponent(filterFormLayout);

        buildTableLayout();
        verticalLayoutSol.addComponent(tableLayout);

        VerticalLayout verticalLayoutSag = new VerticalLayout();
        horizontalLayout.addComponent(verticalLayoutSag);
        buildFormLayout();
        verticalLayoutSag.addComponent(formLayout);

        buildButtonLayout();
        verticalLayoutSag.addComponent(buttonLayout);

        FilmCategoryService filmCategoryService = new FilmCategoryService();
        List<FilmCategory> filmCategoryList = filmCategoryService.findAll();
        fillTable(filmCategoryList);
    }

    private void buildFilterFormLayout() {
        filterFormLayout = new FormLayout();

        filmFilter = new TextField();
        filmFilter.setCaption("Film Name");
        filterFormLayout.addComponent(filmFilter);

        categoryFilter = new CategoryComboBox();
        categoryFilter.setCaption("Category Name");
        filterFormLayout.addComponent(categoryFilter);

        languageFilter = new LanguageComboBox();
        languageFilter.setCaption("Language");
        filterFormLayout.addComponent(languageFilter);

        buildSearchButton();
        filterFormLayout.addComponent(searchButton);
    }

    private void buildSearchButton() {

        searchButton = new Button();
        searchButton.setCaption("Search");
        searchButton.setIcon(FontAwesome.SEARCH);



        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                Film film = new Film();
                film.setTitle(filmFilter.getValue());

                FilmCategoryQueryFilterDto filmCategoryQueryFilterDto = new FilmCategoryQueryFilterDto();
                if (!filmFilter.getValue().equals("")) filmCategoryQueryFilterDto.setFilm(film);
                if (!categoryFilter.getValue().equals("")) filmCategoryQueryFilterDto.setCategory((Category) categoryFilter.getValue());
                if (languageFilter.getValue() != null) filmCategoryQueryFilterDto.setLanguage((Language) languageFilter.getValue());

                FilmCategoryService filmCategoryService = new FilmCategoryService();
                List<FilmCategory> filmList = filmCategoryService.findAllByQueryFilterDto(filmCategoryQueryFilterDto);
                fillTable(filmList);
            }
        });
    }

    private void buildTableLayout() {
        tableLayout = new VerticalLayout();

        buildTable();
        tableLayout.addComponent(table);
    }

    private void buildTable() {
        table = new Table();
        table.setSelectable(true);

        table.addContainerProperty("film", String.class, null);
        table.addContainerProperty("category", String.class, null);
        table.addContainerProperty("language", String.class, null);
        table.addContainerProperty("lastUpdate", Date.class, null);


        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                FilmCategory film = (FilmCategory) itemClickEvent.getItemId();
                fillFormLayoutByFilm(film);
            }
        });
    }

    private void fillFormLayoutByFilm(FilmCategory film) {

        filmNameField.setValue(film.getFilm().getTitle());
        categoryNameField.setValue(film.getCategory().getName());
        languageField.setValue(film.getFilm().getLanguageId());
    }

    private void buildFormLayout() {

        formLayout = new FormLayout();

        filmNameField = new TextField();
        filmNameField.setCaption("Film");
        formLayout.addComponent(filmNameField);

        categoryNameField = new CategoryComboBox();
        categoryNameField.setCaption("Category");
        formLayout.addComponent(categoryNameField);

        languageField = new LanguageComboBox();
        languageField.setCaption("Language");
        formLayout.addComponent(languageField);
    }

    private void buildButtonLayout() {

        buttonLayout = new HorizontalLayout();

        buildSaveButton();
        buttonLayout.addComponent(saveButton);

        buildDeleteButton();
        buttonLayout.addComponent(deleteButton);
    }

    private void buildSaveButton() {
        saveButton = new Button();
        saveButton.setCaption("Save");
        saveButton.setIcon(FontAwesome.SAVE);
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                FilmCategory filmCategory = new FilmCategory();

                filmCategory.setFilm(new Film());
                filmCategory.setCategory((Category) categoryNameField.getValue());

                filmCategory.setLastUpdate(new Date());

                FilmCategoryService filmCategoryService = new FilmCategoryService();
                filmCategoryService.save(filmCategory);

                List<FilmCategory> filmList = filmCategoryService.findAll();
                fillTable(filmList);
            }
        });
    }

    private void buildDeleteButton() {

        deleteButton = new Button();
        deleteButton.setCaption("Delete");
        deleteButton.setIcon(FontAwesome.TRASH);
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                FilmCategory film = new FilmCategory();

                Film film1 = new Film();
                film1.setTitle(filmNameField.getValue());
                film.setFilm(film1);

                FilmCategoryService filmCategoryService = new FilmCategoryService();
                filmCategoryService.delete(film);

                List<FilmCategory> filmList = filmCategoryService.findAll();
                fillTable(filmList);
            }
        });
    }

    private void fillTable(List<FilmCategory> filmList) {

        table.removeAllItems();
        for (FilmCategory film : filmList) {
            Item item = table.addItem(film);
            item.getItemProperty("film").setValue(film.getFilm().getTitle());
            item.getItemProperty("category").setValue(film.getCategory().getName());
            item.getItemProperty("language").setValue(film.getFilm().getLanguageId().getName());
            item.getItemProperty("lastUpdate").setValue(film.getLastUpdate());
        }
    }
}
