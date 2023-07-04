package com.vj.studio.modernjava;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class DishTest {

    Logger logger = LoggerFactory.getLogger(DishTest.class);

    @Test
    public void getHighCaloriesNames() {

        List<Dish> menu = Dish.menu;

        List<String> names = menu.stream().
                filter(dish -> {
                    logger.info("filtering dish: {} by calories", dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    logger.info("mapping {} ", dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());
        logger.info(names.toString());
        assertEquals(3, names.size());

    }

    @Test
    void getLowCaloricDishesNamesInJava7() {


        List<Dish> menu = Dish.menu;
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        logger.info("{}", lowCaloricDishesName);
        assertEquals("[season fruit, rice]", lowCaloricDishesName.toString());
    }

    @Test

    void  getLowCaloricDishesNamesInJava8() {

        List<Dish> menu = Dish.menu;
        List<String> collect = menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());

        logger.info("{}", collect);
        assertEquals("[season fruit, rice]", collect.toString());
    }


}