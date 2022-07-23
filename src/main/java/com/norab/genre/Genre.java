package com.norab.genre;

public class Genre {
    private Integer movieId;
    private String genre;

    public Genre() {
    }

    public Genre(Integer movieId, String genre) {
        this.movieId = movieId;
        this.genre = genre;
    }

    public boolean isValid() {
        return movieId != null && genre != null && !genre.isBlank();
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
            "movieId=" + movieId +
            ", genre='" + genre + '\'' +
            '}';
    }
}
