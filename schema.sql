CREATE TABLE movies (
        movie_id INTEGER PRIMARY KEY,
        title TEXT NOT NULL,
        title_original TEXT,
        release_date DATE NOT NULL,
        orig_movie_id TEXT
    );
CREATE TABLE actors (
        actor_id INTEGER PRIMARY KEY,
        full_name VARCHAR(255) NOT NULL,
        birth_date DATE NOT NULL ,
        death_date DATE DEFAULT NULL,
        orig_name_id TEXT
    );
CREATE TABLE directors (
        actor_id INTEGER,
        movie_id INTEGER,
        CONSTRAINT fk_plays_movie_id
                    FOREIGN KEY (movie_id)
                    REFERENCES movies (movie_id)
                    ON DELETE SET NULL,
        CONSTRAINT fk_plays_actor_id
                    FOREIGN KEY (actor_id)
                    REFERENCES actors (actor_id)
                    ON DELETE SET NULL
    );
CREATE TABLE genre (
        movie_id INTEGER,
        genre TEXT
    );
CREATE TABLE plays (
        role_id INTEGER PRIMARY KEY,
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
