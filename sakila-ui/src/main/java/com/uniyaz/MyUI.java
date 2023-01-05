package com.uniyaz;

import javax.servlet.annotation.WebServlet;

import com.uniyaz.actor.domain.Actor;
import com.uniyaz.actor.service.ActorService;
import com.uniyaz.page.actor.ActorPage;
import com.uniyaz.page.city.CityPage;
import com.uniyaz.page.country.CountryPage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Item;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.uniyaz.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

//        ActorPage actorPage = new ActorPage();
//        setContent(actorPage);

       /* CountryPage countryPage = new CountryPage();
        setContent(countryPage);*/

        CityPage cityPage = new CityPage();
        setContent(cityPage);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
