package tech.examples.xpto.streams;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.examples.xpto.model.Dish;

public class FilteringTest {

  public static final String EXPECTED_VEGETARIAN = "[french fries, rice, season fruit, pizza]";
  private List<Dish> dishes;

  @BeforeEach
  public void init(){
    this.dishes = Dish.menu;
  }

  @Test
  void filteringWithPredicate(){
    List<String> collect = dishes.stream().filter(Dish::isVegetarian)
        .map(Dish::getName)
        .collect(Collectors.toList());

    Assertions.assertEquals(EXPECTED_VEGETARIAN, collect.toString());

  }



}
