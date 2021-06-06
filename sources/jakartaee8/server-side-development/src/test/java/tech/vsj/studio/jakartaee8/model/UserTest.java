package tech.vsj.studio.jakartaee8.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpClass() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void validUser() {
        User user = new User("Fulano", "fulano@emailcom", Arrays.asList(1, 2));
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertTrue(cv.isEmpty());
    }

    @Test
    public void invalidName() {
        User user = new User("", "fulano@emailcom", Arrays.asList(1, 2));
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(1, cv.size());
    }

    @Test
    public void invalidId() {
        User user = new User("elder", "elder@eldermoraes.com", Arrays.asList(-1, -2, 1, 2));
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(2, cv.size());
    }

}
