CREATE TABLE plays (
    role_id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    movie_id BIGINT NULL,
	actor_id BIGINT NULL,
	    CONSTRAINT fk_plays_movie_id
		FOREIGN KEY (movie_id)
		REFERENCES movies (movie_id)
		ON DELETE SET NULL,
		CONSTRAINT fk_plays_actor_id
		FOREIGN KEY (actor_id)
		REFERENCES actors (actor_id)
		ON DELETE SET NULL
);
