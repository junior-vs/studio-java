package tech.examples.xpto.streams;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.examples.xpto.model.Dish;

public class FindingTest {

  private List<Dish> dishes;

  @BeforeEach
  public void init(){
    this.dishes = Dish.menu;
  }

  @Test
  void isVegetarianFriendlyMenu(){
    boolean match = dishes.stream().anyMatch(Dish::isVegetarian);

    Assertions.assertTrue(match);
  }

  @Test
  void isHealthyMenu() {
    boolean match = dishes.stream().allMatch(d -> d.getCalories() < 1000);
    Assertions.assertTrue(match);
  }


  @Test
  void isHealthyMenu2() {
    boolean match = dishes.stream().noneMatch(d -> d.getCalories() >= 1000);
    Assertions.assertTrue(match);
  }

  @Test
  void findVegetarianDish() {
    Optional<Dish> any = dishes.stream().filter(Dish::isVegetarian).findAny();
    System.out.println(any.orElseThrow().getName());
    Assertions.assertTrue(any.isPresent());
  }



}
