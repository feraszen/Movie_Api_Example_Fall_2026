
package com.movieapi.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Iterable<Movie> findMovieByGenre(String genre);

    Iterable<Movie> findByTitleContainingIgnoreCase(String title);
}
