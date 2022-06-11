package com.norab.role_photo;

public record RolePhoto(Long id,
                        String photoUrl,
                        Long movieId,
                        Long actorId,
                        Long roleId) {
}
