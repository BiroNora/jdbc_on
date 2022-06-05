CREATE TABLE role_photos (
    photo_id BIGSERIAL PRIMARY key,
    url TEXT,
    movie_id BIGINT NULL,
    actor_id BIGINT NULL,
    role_id BIGINT NULL,
    unique (url)
);
