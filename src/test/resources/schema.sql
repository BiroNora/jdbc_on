create TABLE actor (
    actor_id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL ,
    death_date DATE DEFAULT NULL,
    unique (full_name)
);

CREATE TABLE movie (
    movie_id BIGSERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    title_original TEXT,
    release_date DATE NOT NULL
);

CREATE TABLE plays (
    role_id BIGSERIAL PRIMARY KEY,
    role_name TEXT NOT NULL,
    movie_id BIGSERIAL NOT NULL REFERENCES movie(movie_id),
	actor_id BIGSERIAL NOT NULL REFERENCES actor(actor_id)
);

CREATE TABLE role_photos (
    photo_id BIGSERIAL PRIMARY KEY,
    url TEXT,
    movie_id BIGINT NULL,
    actor_id BIGINT NULL,
    role_id BIGINT NULL
);

