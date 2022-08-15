package com.norab.show.article;

import com.norab.exception.NotFoundException;
import com.norab.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {
    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public List<Article> listAllArticlesByUsers(Page page) {
        return articleDao.listAllArticlesByUsers(page);
    }

    public List<Article> listAllArticlesByMovies(Page page) {
        return articleDao.listAllArticlesByMovies(page);
    }

    public int insertArticle(Article article) throws IllegalStateException {
        return articleDao.insertArticle(article);
    }

    public int deleteArticle(Integer artId) {
        return articleDao.deleteArticle(artId);
    }

    public Optional<Article> selectArticlesByMovieId(Integer movieId, Page page) {
        return articleDao.selectArticlesByMovieId(movieId, page);
    }

    public Optional<Article> selectArticlesByUserId(UUID userId, Page page) {
        return articleDao.selectArticlesByUserId(userId, page);
    }

    public void updateArticle(Integer artId, Article article) {
        if (articleDao.selectArticleById(artId).isPresent()) {
            Article article1 = new Article(
                artId,
                article.getUserId(),
                article.getBody(),
                article.getStar(),
                article.getMovieId()
            );
            articleDao.updateArticle(artId, article1);
        } else {
            throw new NotFoundException(String.format("Article with id %s not found", artId));
        }
    }
}
