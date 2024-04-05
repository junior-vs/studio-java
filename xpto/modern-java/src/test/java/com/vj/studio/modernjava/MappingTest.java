package com.vj.studio.modernjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vj.studio.javase.updates.Dish;

import java.util.List;
import java.util.stream.Collectors;

public class MappingTest {

    List<Dish> menu = Dish.menu;
    Logger logger = LoggerFactory.getLogger(MappingTest.class);

    @Test
    public void getListName(){
        List<String> collect = menu.stream().map(Dish::getName).collect(Collectors.toList());
    logger.info(collect.toString());
        Assertions.assertEquals(10, collect.size());
    }
}
