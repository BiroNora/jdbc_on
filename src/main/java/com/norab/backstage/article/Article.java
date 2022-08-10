package com.norab.backstage.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Article {
    @Id
    @JsonIgnore
    private Integer artId;
    private UUID userId;
    private String article;
    private Integer star;
    private Integer movieId;

    public Article(UUID userId, String article, Integer star, Integer movieId) {
        this.userId = userId;
        this.article = article;
        this.star = star;
        this.movieId = movieId;
    }

    public Article() {
    }

    public Article(Integer artId, UUID userId, String article, Integer star, Integer movieId) {
        this.artId = artId;
        this.userId = userId;
        this.article = article;
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

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
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
            ", article='" + article + '\'' +
            ", star=" + star +
            ", movieId=" + movieId +
            '}';
    }
}
