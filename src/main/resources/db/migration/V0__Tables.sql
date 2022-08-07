CREATE TABLE actors (
    actor_id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    birth_date SMALLINT NULL,
    death_date SMALLINT DEFAULT NULL
);

CREATE TABLE movies (
    movie_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    title_original VARCHAR(255),
    release_date SMALLINT NOT NULL,
    end_date SMALLINT NULL,
    m_type TEXT,
    is_adult BOOLEAN
);

CREATE TABLE plays (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    movie_id INTEGER NULL,
    actor_id INTEGER NULL,
    CONSTRAINT fk_plays_movie_id
        FOREIGN KEY (movie_id)
        REFERENCES movies (movie_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_plays_actor_id
        FOREIGN KEY (actor_id)
        REFERENCES actors (actor_id)
        ON DELETE CASCADE
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

CREATE TABLE directors (
    actor_id INTEGER,
    movie_id INTEGER,
    CONSTRAINT fk_directors_movie_id
        FOREIGN KEY (movie_id)
        REFERENCES movies (movie_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_directors_actor_id
        FOREIGN KEY (actor_id)
        REFERENCES actors (actor_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_directors PRIMARY KEY (actor_id, movie_id)
);

CREATE TABLE genre (
    movie_id INTEGER NOT NULL,
    genre VARCHAR(40) NOT NULL,
    CONSTRAINT fk_genre_movie_id
        FOREIGN KEY (movie_id)
        REFERENCES movies (movie_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_genre PRIMARY KEY (movie_id, genre)
);
