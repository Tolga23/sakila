package com.uniyaz.actor.service;

import com.uniyaz.actor.domain.Actor;
import org.junit.Test;

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

}
