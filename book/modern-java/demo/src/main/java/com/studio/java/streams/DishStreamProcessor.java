package com.studio.java.streams;

import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.studio.java.domain.Dish;

public class DishStreamProcessor {


    /**
     * This method takes a list of Dish objects, filters out the dishes with calories greater than 300,
     * maps the remaining dishes to their names, limits the result to the first 3 dishes, and returns a list of these names.
     *
     * @param dishes A list of Dish objects.
     * @return A list of strings containing the names of the dishes with calories greater than 300, limited to the first 3.
     */
    public List<String> highCaloricDishesNames(List<Dish> dishes) {

        // Convert the list of Dish objects to a stream
        return dishes
                // Filter the stream to include only dishes with calories > 300
                .stream()
                .filter(dish -> dish.calories() > 300)
                // Map each remaining Dish object to its name
                .map(Dish::name)
                // Limit the stream to the first 3 names
                .limit(3)
                // Convert the stream back to a list
                .toList();
    }

    /**
     * This method takes a list of Dish objects, filters out the dishes with calories less than 400,
     * sorts the remaining dishes by calories in ascending order, maps the dishes to their names,
     * and returns a list of these names.
     *
     * @param dishes A list of Dish objects.
     * @return A list of strings containing the names of the dishes with calories less than 400,
     *         sorted by calories.
     */
    public List<String> lowCaloricDishesNames(List<Dish> dishes) {
        // Convert the list of Dish objects to a stream
        return dishes
                .stream()
                // Filter the stream to include only dishes with calories less than 400
                .filter(dish -> dish.calories() < 400)
                // Sort the remaining dishes by calories in ascending order
                .sorted(Comparator.comparing(Dish::calories))
                // Map each remaining Dish object to its name
                .map(Dish::name)
                // Convert the stream back to a list
                .toList();
    }

    /**
     * This method filters out the dishes that are vegetarian from a list of Dish objects and returns a list of their names.
     *
     * @param dishes A list of Dish objects.
     * @return A list of strings containing the names of the vegetarian dishes.
     */
    List<String> onlyVegetarians(List<Dish> dishes) {
        return dishes.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::name)
                .toList();
    }

    



}
