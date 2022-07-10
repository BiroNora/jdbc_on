CREATE TABLE photos (
    photo_id SERIAL PRIMARY KEY,
    url TEXT,
    movie_id INTEGER NULL,
    actor_id INTEGER NULL,
    role_id  INTEGER NULL,
		CONSTRAINT fk_photos_movie_id
			FOREIGN KEY (movie_id)
			REFERENCES movies (movie_id)
			ON DELETE SET NULL,
		CONSTRAINT fk_photos_actor_id
			FOREIGN KEY (actor_id)
			REFERENCES actors (actor_id)
			ON DELETE SET NULL,
		CONSTRAINT fk_photos_role_id
			FOREIGN KEY (role_id)
			REFERENCES plays (role_id)
			ON DELETE SET NULL
);