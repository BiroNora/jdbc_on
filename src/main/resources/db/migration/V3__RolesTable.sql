CREATE TABLE plays (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    movie_id INTEGER NULL,
		actor_id INTEGER NULL,
		CONSTRAINT fk_plays_movie_id
			FOREIGN KEY (movie_id)
			REFERENCES movies (movie_id)
			ON DELETE SET NULL,
		CONSTRAINT fk_plays_actor_id
			FOREIGN KEY (actor_id)
			REFERENCES actors (actor_id)
			ON DELETE SET NULL
);
