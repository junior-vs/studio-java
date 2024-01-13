package br.com.studio.java.refl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Transformator {



    public <I, O> O transformToDTO(I input)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {

        Class<?> source = input.getClass();
        Class<?> target = Class.forName(source.getName() + "DTO");

        O targetclass = (O) target.getDeclaredConstructor().newInstance();
        Field[] sourceDeclaredFields = source.getDeclaredFields();
        Field[] targetDeclaredFields = target.getDeclaredFields();

        Arrays.stream(sourceDeclaredFields).forEach((sourceField) -> {
            Arrays.stream(targetDeclaredFields).forEach((targetField) -> {
                validateField(sourceField, targetField);
                try {
                    targetField.set(targetclass, sourceField.get(input));
                } catch (IllegalAccessException e) {
                   e.printStackTrace();
                }
            });
        });

        return targetclass;
    }

    private void validateField(Field sourceField, Field targetField) {
        if (sourceField.getName().equals(targetField.getName())
                && sourceField.getType().equals(targetField.getType())) {
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
        }
    }

}
