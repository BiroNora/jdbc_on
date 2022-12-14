package com.norab.show.article;

import com.norab.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface ArticleDao<Article> {
    List<Article> listAllArticlesByUsers(Page page);

    List<Article> listAllArticlesByMovies(Page page);

    int insertArticle(Article article) throws IllegalStateException;

    boolean deleteArticle(Integer artId);

    Optional<Article> selectArticleById(Integer actId);

    List<Article> selectArticlesByMovieId(Integer movieId, Page page);

    List<Article> selectArticlesByUserId(UUID userId, Page page);

    boolean updateArticle(Integer artId, Article article);
}
