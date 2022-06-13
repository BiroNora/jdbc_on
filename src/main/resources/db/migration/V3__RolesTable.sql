CREATE TABLE plays (
    role_id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    movie_id BIGSERIAL NOT NULL REFERENCES movies(movie_id),
	actor_id BIGSERIAL NOT NULL REFERENCES actors(actor_id)
);
