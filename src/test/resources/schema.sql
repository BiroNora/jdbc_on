create TABLE actors (
    actor_id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL ,
    death_date DATE DEFAULT NULL,
    unique (full_name)
);

CREATE TABLE movies (
    movie_id BIGSERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    title_original TEXT,
    release_date DATE NOT NULL
);
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

CREATE TABLE photos (
    photo_id BIGSERIAL PRIMARY KEY,
    url TEXT NOT NULL,
    movie_id BIGINT NULL,
    actor_id BIGINT NULL,
    role_id BIGINT NULL,
		CONSTRAINT fk_photos_movie_id
		FOREIGN KEY (movie_id)
		REFERENCES movies (movie_id),
		CONSTRAINT fk_photos_actor_id
		FOREIGN KEY (actor_id)
		REFERENCES actors (actor_id),
		CONSTRAINT fk_photos_role_id
		FOREIGN KEY (role_id)
		REFERENCES plays (role_id)
);
