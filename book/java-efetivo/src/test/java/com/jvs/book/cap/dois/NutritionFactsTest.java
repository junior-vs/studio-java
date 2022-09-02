package com.jvs.book.cap.dois;

import org.junit.jupiter.api.Test;

public class NutritionFactsTest {

  @Test
  void createObjTest() {
    NutritionFacts coca = new NutritionFacts.Builder(240, 8)
      .withCalaories(100)
      .withSodium(35)
      .withCarbohydrate(27)
      .build();

      System.out.println(coca);
  }
}
