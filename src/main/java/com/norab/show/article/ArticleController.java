package com.norab.show.article;

import com.norab.backstage.user.User;
import com.norab.utils.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("{id}")
    public Article selectArticleById(
        @PathVariable("id") Integer artId) {
        return articleService.selectArticleById(artId);
    }

    @GetMapping("/byuser")
    public List<Article> listAllArticlesByUsers(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return articleService.listAllArticlesByUsers(Page.of(page, size));
    }

    @GetMapping("/bymovie")
    public List<Article> listAllArticlesByMovies(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return articleService.listAllArticlesByUsers(Page.of(page, size));
    }

    @GetMapping("/movie/{id}")
    public List<Article> selectArticlesByMovieId(
        @PathVariable("id") Integer movieId,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return articleService.selectArticlesByMovieId(movieId, Page.of(page, size));
    }

    @GetMapping("/user/{id}")
    public List<Article> selectArticlesByUserId(
        @PathVariable("id") UUID userId,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return articleService.selectArticlesByUserId(userId, Page.of(page, size));
    }

    @PostMapping
    public ArticleID insertArticle(
        @RequestBody Article article) {
        return new ArticleID(articleService.insertArticle(article));
    }

    @DeleteMapping("{id}")
    public boolean deleteArticle(
        @PathVariable("id") Integer artId) {
        return articleService.deleteArticle(artId);
    }


    @PutMapping("{id}")
    public void updateArticle(
        @PathVariable("id") Integer artId,
        @RequestBody Article article) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            String username = ((UserDetails) principal).getUsername();
            System.out.println(username);
        } else {
            String username = principal.toString();
            System.out.println("WWW: " + username);
        }
        articleService.updateArticle(artId, article);
    }
}
