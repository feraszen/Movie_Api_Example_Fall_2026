
package com.movieapi.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;
    private String genre;
    private String releaseDate;

    private boolean availableInIMAX;
    private boolean availableIn3D;

    public Movie() {
    }

    public Movie(String title, String genre, String releaseDate) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public Movie(
            Long movieId,
            String title,
            String genre,
            String releaseDate) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isAvailableInIMAX() {
        return availableInIMAX;
    }

    public void setAvailableInIMAX(boolean availableInIMAX) {
        this.availableInIMAX = availableInIMAX;
    }

    public boolean isAvailableIn3D() {
        return availableIn3D;
    }

    public void setAvailableIn3D(boolean availableIn3D) {
        this.availableIn3D = availableIn3D;
    }
}

