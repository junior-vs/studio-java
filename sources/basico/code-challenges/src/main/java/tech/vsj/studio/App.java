package tech.vsj.studio;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class App {

  // new App(10).fly(5);
  public static void main(String[] args) {

    int time = 9;
    int day = 3;
    var dinner = ++time >= 10 ? day-- <= 2
    ? "Takeout" : "Salad" : "Leftovers";
    System.out.println(dinner);
    
  }
}
