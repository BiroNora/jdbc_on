CREATE TABLE role_photos (
    photo_id BIGSERIAL PRIMARY key,
    url TEXT,
    movie_id BIGSERIAL REFERENCES movie(movie_id),
    actor_id BIGSERIAL REFERENCES actor(actor_id),
    role_id BIGSERIAL REFERENCES plays(role_id),
    unique (url)
);
