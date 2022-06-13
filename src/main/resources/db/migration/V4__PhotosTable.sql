CREATE TABLE photos (
    photo_id BIGSERIAL PRIMARY KEY,
    url TEXT,
    movie_id BIGINT NULL,
    actor_id BIGINT NULL,
    role_id BIGINT NULL
);