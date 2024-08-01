package com.studio.java.domain;

import java.util.Arrays;
import java.util.List;

public record Dish(
        String name,
        boolean vegetarian,
        int calories,
        Type type) {



    public boolean isVegetarian() {
        return vegetarian;
    }
}
