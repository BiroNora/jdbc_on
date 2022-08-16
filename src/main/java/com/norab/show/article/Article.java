package com.norab.show.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Article {
    @Id
    private Integer artId;
    private UUID userId;
    private String body;
    private Short star;
    private Integer movieId;

    public Article(UUID userId, String body, Short star, Integer movieId) {
        this.userId = userId;
        this.body = body;
        this.star = star;
        this.movieId = movieId;
    }

    public Article() {
    }

    public Article(Integer artId, UUID userId, String body, Short star, Integer movieId) {
        this.artId = artId;
        this.userId = userId;
        this.body = body;
        this.star = star;
        this.movieId = movieId;
    }

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Short getStar() {
        return star;
    }

    public void setStar(Short star) {
        this.star = star;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Article{" +
            "artId=" + artId +
            ", userId=" + userId +
            ", body='" + body + '\'' +
            ", star=" + star +
            ", movieId=" + movieId +
            '}';
    }
}
