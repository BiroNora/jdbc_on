package com.norab.backstage.user;

public record Article(
    Integer artId,
    Integer userId,
    String article,
    Integer stars,
    Integer movieId
) {
}
