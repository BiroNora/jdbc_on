package com.norab.photo;

public record Photo(Long id,
                    String photoUrl,
                    Long movieId,
                    Long actorId,
                    Long roleId) {
}
