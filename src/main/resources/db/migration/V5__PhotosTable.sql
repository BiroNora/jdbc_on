DROP TABLE IF EXISTS photos;

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