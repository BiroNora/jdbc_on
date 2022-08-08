package com.norab.backstage.user;

import java.util.UUID;

public record Article(
    Integer artId,
    UUID userId,
    String article,
    Integer stars,
    Integer movieId
) {
}
