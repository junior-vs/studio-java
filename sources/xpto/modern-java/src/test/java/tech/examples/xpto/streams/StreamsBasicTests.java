package tech.examples.xpto.streams;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.examples.xpto.model.Dish;

public class StreamsBasicTests {

  public static final String DATA_TEXT_TXT = "data-text.txt";
  private List<Dish> dishes;

  @BeforeEach
  public void init(){
    this.dishes = Dish.menu;
  }

  @Test
  public void getLowCaloricDishesNameTest(){
    Optional<String> first = this.dishes
        .stream()
        .sorted(Comparator.comparing(Dish::getCalories))
        .map(Dish::getName)
        .findFirst();

    Assertions.assertEquals("season fruit", first.orElseThrow());
  }

  @Test
  public void countUniqueWordInFile() throws IOException {

    File file = getFile();

    long count = Files.lines(file.toPath()).flatMap(lines -> Arrays.stream(lines.split(" ")))
        .distinct().count();

    Assertions.assertEquals(9, count);

  }

  private static File getFile() {
    URL url = Thread.currentThread().getContextClassLoader().getResource(DATA_TEXT_TXT);
    return new File(Objects.requireNonNull(url).getPath());
  }


}
