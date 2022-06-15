package com.norab.movie;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Movie{
    @Id
    private Long id;
    private String title;
    private String titleOriginal;
    LocalDate releaseDate;

    public Movie() {
    }

    public Movie(String title, String titleOriginal, LocalDate releaseDate) {
        this.title = title;
        this.titleOriginal = titleOriginal;
        this.releaseDate = releaseDate;
    }

    public Movie(Long id, String title, String titleOriginal, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.titleOriginal = titleOriginal;
        this.releaseDate = releaseDate;
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

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", titleOriginal='" + titleOriginal + '\'' +
            ", releaseDate=" + releaseDate +
            '}';
    }
}


