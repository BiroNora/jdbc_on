CREATE TABLE actor (
    actor_id BIGSERIAL PRIMARY key,
    full_name TEXT NOT null,
    birth_date DATE NOT NULL,
    movie_id BIGSERIAL,
    unique (full_name)
);