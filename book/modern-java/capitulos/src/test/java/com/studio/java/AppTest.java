package com.studio.java;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        App app = new App();
        assertTrue(true);
        assertTrue(app.teste(1));
        assertTrue(app.teste(2));
        assertTrue(app.teste(3));
        assertFalse(app.teste(0));
        assertFalse(app.teste(4));
    }
}
