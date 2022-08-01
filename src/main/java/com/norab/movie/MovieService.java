package com.norab.movie;

import com.norab.exception.AlreadyExistsException;
import com.norab.exception.InternalServerExeption;
import com.norab.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieDao movieDao;

    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public List<Movie> getMovies() {
        return movieDao.selectMovies();
    }

    public int insertMovie(Movie movie) {
        String movieTitle = movie.getTitle();
        List<Movie> movies = movieDao.selectMovies();
        List<Movie> collect = movies.stream()
            .filter(x -> x.getTitle().equals(movieTitle)).toList();
        if (collect.size() != 0) {
            throw new AlreadyExistsException("this movie already exists");
        }
        return movieDao.insertMovie(movie);
    }

    public void deleteMovie(Integer movieId, boolean force) {
        switch (movieDao.deleteMovie(movieId, force)) {
            case INVALID_ID:
                throw new NotFoundException(String.format("Movie with id %s not found", movieId));
            case JDBC_ERROR:
                throw new InternalServerExeption("Could not delete movie");
            case HAS_REFERENCES:
                throw new AlreadyExistsException("Warning: this movie has references");
            case SUCCESS:
        }
    }

    public Movie getMovie(Integer movieId) {
        try {
            return (Movie) movieDao.selectMovieById(movieId)
                .orElseThrow(() -> new NotFoundException(String.format("Movie with id %s not found", movieId)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMovie(Integer movieId, Movie movie) {
        if (movieDao.selectMovieById(movieId).isPresent()) {
            Movie movie1 = new Movie(
                movieId,
                movie.getTitle(),
                movie.getTitleOriginal(),
                movie.getReleaseDate(),
                movie.getEndDate(),
                movie.getmType(),
                movie.isAdult()
            );
            movieDao.updateMovie(movieId, movie1);
        } else {
            throw new NotFoundException(String.format("Movie with id %s not found", movieId));
        }
    }

}
