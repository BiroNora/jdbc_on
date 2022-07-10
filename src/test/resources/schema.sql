create TABLE actors (
    actor_id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL ,
    death_date DATE DEFAULT NULL
);

CREATE TABLE movies (
    movie_id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    title_original TEXT,
    release_date DATE NOT NULL,
    movie_film BOOLEAN NOT NULL
);
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

CREATE TABLE photos (
    photo_id SERIAL PRIMARY KEY,
    url TEXT NOT NULL,
    movie_id INTEGER NULL,
    actor_id INTEGER NULL,
    role_id INTEGER NULL,
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
