
package com.movieapi.movie;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class MovieServiceTest {

    @Test
    void shouldStoreIMAXAnd3DAvailability() {
        Movie movie = new Movie(
                "Avatar Test",
                "Science Fiction",
                "2026-10-21"
        );

        movie.setAvailableInIMAX(true);
        movie.setAvailableIn3D(true);

        assertTrue(movie.isAvailableInIMAX());
        assertTrue(movie.isAvailableIn3D());
    }

    @Test
    void shouldAllowIMAXWithout3D() {
        Movie movie = new Movie(
                "IMAX Only Test",
                "Action",
                "2026-10-21"
        );

        movie.setAvailableInIMAX(true);
        movie.setAvailableIn3D(false);

        assertTrue(movie.isAvailableInIMAX());
        assertFalse(movie.isAvailableIn3D());
    }

    @Test
    void shouldAllow3DWithoutIMAX() {
        Movie movie = new Movie(
                "3D Only Test",
                "Adventure",
                "2026-10-21"
        );

        movie.setAvailableInIMAX(false);
        movie.setAvailableIn3D(true);

        assertFalse(movie.isAvailableInIMAX());
        assertTrue(movie.isAvailableIn3D());
    }
}
