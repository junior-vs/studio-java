package com.studio.java.streams;

import com.studio.java.App;
import com.studio.java.data.DishData;
import com.studio.java.domain.Dish;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DishStreamProcessorTest {

    private static final Logger logger = LogManager.getLogger(App.class);

    DishStreamProcessor dishStreamProcessor;
    final List<Dish> menu = DishData.menu();
    @BeforeEach
    void setUp() {
        dishStreamProcessor = new DishStreamProcessor();
    }


    @Test
    void highCaloricDishesNames() {



        List<String> dishNames = this.dishStreamProcessor.highCaloricDishesNames(menu);
        logger.info(dishNames);

        List<String> expected = List.of("pork", "beef", "chicken");
        assertEquals(expected, dishNames);
    }

    @Test
    void lowCaloricDishesNames() {
        List<String> result = this.dishStreamProcessor.lowCaloricDishesNames(menu);
        logger.info(result);
        List<String> expected = List.of("season fruit", "rice");
        assertEquals(expected, result);

    }

    @Test
    void onlyVegetarians() {
        List<String> result = this.dishStreamProcessor.onlyVegetarians(this.menu);
        List<String> expected = List.of("french fries", "rice", "season fruit","pizza");
        assertEquals(expected, result);
    }
}