package com.vj.studio.modernjava;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DishFilteringTests {

    Logger logger = LoggerFactory.getLogger(DishFilteringTests.class);

    List<Dish> menu;

    @BeforeAll
    void init() {
        this.menu = Dish.menu.stream()
                .sorted(Comparator.comparing(Dish::getCalories))
                .collect(Collectors.toList());
    }

    @Test
    public void filterVegetarian() {

        List<Dish> collect = this.menu.stream().filter(Dish::isVegetarian).distinct().collect(Collectors.toList());
        collect.forEach(d -> logger.info(d.toString()));

        assertEquals(4, collect.size());
    }

    @Test
    public void filterGetTheLowerCalories() {

        List<Dish> collect = this.menu.stream().takeWhile(dish -> dish.getCalories() < 320).collect(Collectors.toList());
        collect.forEach(d -> logger.info(d.toString()));
        assertEquals(2, collect.size());

    }


    /**
     * Para uso com necessidade de perfomanacee.
     * Necessário que o stream esteja
     */
    @Test
    public void filterGetTheBiggestCalories() {

        List<Dish> collect = this.menu.stream().dropWhile(dish -> dish.getCalories() < 320).collect(Collectors.toList());
        collect.forEach(d -> logger.info(d.toString()));
        assertEquals(8, collect.size());

    }

    @Test
    public void filterGetThe3BiggestCalories() {

        List<Dish> collect = this.menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        collect.forEach(d -> logger.info(d.toString()));
        assertEquals(3, collect.size());

    }

    @Test
    public void skippingElements() {

        List<Dish> collect = this.menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        collect.forEach(d -> logger.info(d.toString()));
        assertEquals(6, collect.size());

    }

    @Test
    public void getTwoFirstMeats() {
        List<Dish> collected = this.menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2).collect(Collectors.toList());

        logger.info(collected.toString());
        assertEquals(2, collected.size());
    }

    @Test

    public void usedFlatMapping() {

        List<String> words = Arrays.asList("Hello", "World");

        List<String> collect = words.stream()
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct()
                .collect(Collectors.toList());
        logger.info(collect.toString());
    }
}
