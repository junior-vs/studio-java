package com.jvs.book.cap.dois;

public class NutritionFacts {

  private final int servingSize;
  private final int servings;
  private final int calaories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  public static class Builder {
    private final int servingSize;
    private final int servings;

    // Parametros Opcionais - inicalizado para os valores padrão
    private int calaories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    public Builder withCalaories(int calaories) {
      this.calaories = calaories;
      return this;
    }

    public Builder withFat(int fat) {
      this.fat = fat;
      return this;
    }

    public Builder withSodium(int sodium) {
      this.sodium = sodium;
      return this;
    }

    public Builder withCarbohydrate(int carbohydrate) {
      this.carbohydrate = carbohydrate;
      return this;
    }

    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }

  private NutritionFacts(Builder builder) {
    this.servingSize = builder.servingSize;
    this.servings = builder.servings;
    this.calaories = builder.calaories;
    this.fat = builder.fat;
    this.sodium = builder.sodium;
    this.carbohydrate = builder.carbohydrate;
  }

  @Override
  public String toString() {
    return "NutritionFacts [servingSize=" + servingSize + ", servings=" + servings + ", calaories=" + calaories
        + ", fat=" + fat + ", sodium=" + sodium + ", carbohydrate=" + carbohydrate + "]";
  }

}
