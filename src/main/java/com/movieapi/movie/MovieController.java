
package com.movieapi.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<String> saveNewMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.saveMovie(movie));
    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<Iterable<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long movieId) {
        return ResponseEntity.ok(
            movieService.getMovieById(movieId)
                .orElseThrow(() ->
                    new RuntimeException("Movie not found with id: " + movieId)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }

    @GetMapping("/searchByGenre/{genre}")
    public ResponseEntity<Iterable<Movie>> getByGenre(
            @PathVariable String genre) {
        return ResponseEntity.ok(movieService.findByGenre(genre));
    }

    @GetMapping("/searchByTitle")
    public ResponseEntity<Iterable<Movie>> searchByTitle(
            @RequestParam String title) {
        return ResponseEntity.ok(movieService.findByTitle(title));
    }
}
