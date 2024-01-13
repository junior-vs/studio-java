package br.com.studio.java.refl;

import br.com.studio.java.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectToJsonTest {


    public static void main(String... x) {
        Pessoa pessoa = new Pessoa(1, "João", "12345");
        ObjectToJson objectToJson = new ObjectToJson();
        System.out.println(objectToJson.transform(pessoa));
    }

    @Test
    public void shouldTransform() {
        Pessoa pessoa = new Pessoa(1, "João", "12345");
        ObjectToJson objectToJson = new ObjectToJson();
        String actual = objectToJson.transform(pessoa);

        System.out.println(actual);

        Assertions.assertInstanceOf(String.class, actual);
    }

}