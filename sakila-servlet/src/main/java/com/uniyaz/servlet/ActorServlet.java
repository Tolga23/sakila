package com.uniyaz.servlet;

import com.uniyaz.actor.domain.Actor;
import com.uniyaz.actor.service.ActorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ActorServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Actor actor = new Actor();
        ActorService actorService = new ActorService();

        String username = req.getParameter("username");
        String lastname = req.getParameter("lastname");
        Date date = new Date();


        actor.setFirstName(username);
        actor.setLastName(lastname);
        actor.setLastUpdate(date);

        actorService.save(actor);
        resp.getWriter().write("BASARILI");
    }
}
