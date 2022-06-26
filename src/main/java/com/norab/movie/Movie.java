package com.norab.movie;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Movie{
    @Id
    private Long id;
    private String title;
    private String titleOriginal;
    LocalDate releaseDate;
    boolean isMovieFilm;

    public Movie() {
    }

    public Movie(String title, String titleOriginal, LocalDate releaseDate, boolean isMovieFilm) {
        this.title = title;
        this.titleOriginal = titleOriginal;
        this.releaseDate = releaseDate;
        this.isMovieFilm = isMovieFilm;
    }

    public Movie(Long id, String title, String titleOriginal, LocalDate releaseDate, boolean isMovieFilm) {
        this.id = id;
        this.title = title;
        this.titleOriginal = titleOriginal;
        this.releaseDate = releaseDate;
        this.isMovieFilm = isMovieFilm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleOriginal() {
        return titleOriginal;
    }

    public void setTitleOriginal(String titleOriginal) {
        this.titleOriginal = titleOriginal;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isMovieFilm() {
        return isMovieFilm;
    }

    public void setMovieFilm(boolean movieFilm) {
        isMovieFilm = movieFilm;
    }

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", titleOriginal='" + titleOriginal + '\'' +
            ", releaseDate=" + releaseDate +
            ", isMovieFilm=" + isMovieFilm +
            '}';
    }
}


