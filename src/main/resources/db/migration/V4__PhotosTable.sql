CREATE TABLE role_photos (
    photo_id BIGSERIAL PRIMARY key,
    url TEXT,
    movie_id BIGSERIAL NOT null REFERENCES movie(movie_id),
    actor_id BIGSERIAL NOT null REFERENCES actor(actor_id),
    role_id BIGSERIAL NOT null REFERENCES plays(role_id),
    unique (url)
);
