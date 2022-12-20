package com.cydeo.spacecraft.unit.service;

import com.cydeo.spacecraft.entity.Target;
import com.cydeo.spacecraft.enumtype.Level;
import com.cydeo.spacecraft.service.impl.CreatePlayerServiceImpl;
import com.cydeo.spacecraft.service.impl.CreateTargetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CreateTargetServiceImplTest {

    private CreateTargetServiceImpl createTargetService;

    @BeforeEach//basic value to complete test
    public void setUp(){
        createTargetService = new CreateTargetServiceImpl();
    }

    @Test
    public void should_create_target_with_easy_level(){
        //given
        Level level = Level.EASY;

        //when
        Set<Target> targets = createTargetService.createTargets(level);//

        //then : check
        Target target = targets.stream().findAny().get();
        assertEquals(targets.size(), 1);
        assertEquals(target.getHealth(), 233);
        assertEquals(target.getArmor(), 7);
        assertEquals(target.getShootPower(), 10);

    }


    @Test
    public void should_create_target_with_medium_level(){
        //given
        Level level = Level.MEDIUM;

        //when
        Set<Target> targets = createTargetService.createTargets(level);//

        //then : check
        Target target = targets.stream().findAny().get();

        assertEquals(targets.size(), 3);
        assertEquals(target.getHealth(), 932);
        assertEquals(target.getArmor(), 301);
        assertEquals(target.getShootPower(), 10);



    }

    @Test
    public void should_create_target_with_hard_level(){
        //given
        Level level = Level.HARD;

        //when
        Set<Target> targets = createTargetService.createTargets(level);//

        //then : check
        Target target = targets.stream().findAny().get();

        assertEquals(targets.size(), 4);
        assertEquals(target.getHealth(), 1165);
        assertEquals(target.getArmor(), 581);
        assertEquals(target.getShootPower(), 1000);



    }

    //    throw new RuntimeException("Level type must be valid");

    @Test
    public void should_throw_exception_when_level_type_is_insane(){

        //given
        Level level = Level.INSANE;

        RuntimeException runtimeException = assertThrows(RuntimeException.class , () -> {
            createTargetService.createTargets(level);
        });

        assertEquals(runtimeException.getMessage(),"Level type must be valid");



    }


}
