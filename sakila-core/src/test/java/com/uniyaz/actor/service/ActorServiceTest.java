package com.uniyaz.actor.service;

import com.uniyaz.actor.dao.ActorDao;
import com.uniyaz.actor.domain.Actor;
import com.uniyaz.actor.queryfilterdto.ActorQueryFilterDto;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ActorServiceTest {

    @Test
    public void testFindAll() {
        ActorService actorService = new ActorService();
        List<Actor> actorList = actorService.findAll();

        for (Actor actor : actorList) {
            System.out.println(actor);
        }

    }

    @Test
    public void saveTest(){
        Actor actor = new Actor();
        actor.setFirstName("Generic");
        actor.setLastName("Test");
        actor.setLastUpdate(new Date());

        ActorDao service = new ActorDao();
        service.save(actor);
    }

    @Test
    public void deleteTest(){
        Actor actor = new Actor();

        actor.setId(206l);

        ActorDao actorDao = new ActorDao();
        actorDao.delete(actor);

        System.out.println("Silindi");
    }

    @Test
    public void baseQueryGenericTest(){

        ActorQueryFilterDto actor = new ActorQueryFilterDto();
          actor.setId(1l);
          actor.setFirstName("PENELOPE");

        ActorDao actorDao = new ActorDao();
        List<Actor> actorList = actorDao.findAllByQueryFilterDto(actor);

        for (Actor actor1 : actorList) {
            System.out.println(actor1);
        }


    }

    @Test
    public void baseQueryWithHql(){

        ActorQueryFilterDto actor = new ActorQueryFilterDto();
        actor.setId(1l);
       // actor.setFirstName("PENELOPE");

        ActorDao actorDao = new ActorDao();
        List<Actor> actorList = actorDao.findAllByQueryFilterDtoHql(actor);

        for (Actor actor1 : actorList) {
            System.out.println(actor1);
        }


    }

}
