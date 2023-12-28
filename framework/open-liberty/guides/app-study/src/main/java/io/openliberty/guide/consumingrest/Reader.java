package io.openliberty.guide.consumingrest;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Reader {

  public static JsonArray getArtists() {
    final String jsonFile = "./../../../../../../src/resources/artists.json";
    try {
      InputStream fis;
      fis = new FileInputStream(jsonFile);
      return Json.createReader(fis).readArray();
    } catch (FileNotFoundException e) {
      System.err.println("File does not exist: " + jsonFile);
      return null;
    }
  }
}
