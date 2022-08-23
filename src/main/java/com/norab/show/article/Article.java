package com.norab.show.article;

import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.UUID;

public class Article {
    @Id
    private Integer artId;
    private UUID userId;
    private String body;
    private Short star;
    private Integer movieId;

    public Article() {
    }

    public Article(UUID userId, String body, Short star, Integer movieId) {
        this.userId = userId;
        this.body = body;
        this.star = star;
        this.movieId = movieId;
    }

    public Article(Integer artId, UUID userId, String body, Short star, Integer movieId) {
        this.artId = artId;
        this.userId = userId;
        this.body = body;
        this.star = star;
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!artId.equals(article.artId)) return false;
        if (!userId.equals(article.userId)) return false;
        if (!Objects.equals(body, article.body)) return false;
        if (!star.equals(article.star)) return false;
        return movieId.equals(article.movieId);
    }

    @Override
    public int hashCode() {
        int result = artId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + star.hashCode();
        result = 31 * result + movieId.hashCode();
        return result;
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
