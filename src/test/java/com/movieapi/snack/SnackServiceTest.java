
package com.movieapi.snack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class SnackServiceTest {

    @Test
    void shouldCreateSnackWithCorrectValues() {
        Snack snack = new Snack("Buttered Popcorn", "Salty", 8.50, true);

        assertEquals("Buttered Popcorn", snack.getName());
        assertEquals("Salty", snack.getType());
        assertEquals(8.50, snack.getPrice(), 0.001);
        assertTrue(snack.isShareable());
    }

    @Test
    void shouldUpdateSnackValues() {
        Snack snack = new Snack("Popcorn", "Salty", 5.00, false);

        snack.setName("Chocolate");
        snack.setType("Sweet");
        snack.setPrice(7.50);
        snack.setShareable(true);

        assertEquals("Chocolate", snack.getName());
        assertEquals("Sweet", snack.getType());
        assertEquals(7.50, snack.getPrice(), 0.001);
        assertTrue(snack.isShareable());
    }

    @Test
    void shouldSetAndGetSnackId() {
        Snack snack = new Snack("Popcorn", "Salty", 5.00, false);

        snack.setSnackId(10L);

        assertEquals(10L, snack.getSnackId());
    }

    @Test
    void shouldAcceptZeroPrice() {
        Snack snack = new Snack("Free Sample", "Sweet", 0.0, false);

        assertEquals(0.0, snack.getPrice(), 0.001);
    }
}
