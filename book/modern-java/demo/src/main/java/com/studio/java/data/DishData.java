package com.studio.java.data;

import com.studio.java.domain.Dish;
import com.studio.java.domain.Type;

import java.util.Arrays;
import java.util.List;

public final class DishData {



    public static final List<Dish> menu(){
        return Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 400, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH));
    }


}
