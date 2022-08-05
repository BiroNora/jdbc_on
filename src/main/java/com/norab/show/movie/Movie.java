package com.norab.show.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.norab.utils.ToJsonString;
import org.springframework.data.annotation.Id;

public class Movie extends ToJsonString {
    @Id
    @JsonIgnore
    private Integer movieId;
    private String title;
    private String titleOriginal;
    private Short releaseDate;
    private Short endDate;
    private String mType;
    private boolean isAdult;

    public Movie() {
    }

    public Movie(String title, String titleOriginal, Short releaseDate) {
        this.title = title;
        this.titleOriginal = titleOriginal;
        this.releaseDate = releaseDate;
    }

    public Movie(Integer movieId, String title, String titleOriginal, Short releaseDate) {
        this.movieId = movieId;
        this.title = title;
        this.titleOriginal = titleOriginal;
        this.releaseDate = releaseDate;
    }

    public Movie(String title, String titleOriginal, Short releaseDate, Short endDate, String mType, boolean isAdult) {
        this.title = title;
        this.titleOriginal = titleOriginal;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.mType = mType;
        this.isAdult = isAdult;
    }

    public Movie(Integer movieId, String title, String titleOriginal, Short releaseDate, Short endDate, String mType, boolean isAdult) {
        this.movieId = movieId;
        this.title = title;
        this.titleOriginal = titleOriginal;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.mType = mType;
        this.isAdult = isAdult;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
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

    public Short getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Short releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Short getEndDate() {
        return endDate;
    }

    public void setEndDate(Short endDate) {
        this.endDate = endDate;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    @Override
    public String toString() {
        return "Movie{" +
            "movieId=" + movieId +
            ", title='" + title + '\'' +
            ", titleOriginal='" + titleOriginal + '\'' +
            ", releaseDate=" + releaseDate +
            ", endDate=" + endDate +
            ", mType='" + mType + '\'' +
            ", isAdult=" + isAdult +
            '}';
    }
}
