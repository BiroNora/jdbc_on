package com.norab.movie;

import com.norab.crossed.CrossedDao;
import com.norab.crossed.SearchLocation;
import com.norab.exception.AlreadyExistsException;
import com.norab.exception.InternalServerExeption;
import com.norab.exception.NotFoundException;
import com.norab.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieDao movieDao;
    private final CrossedDao crossedDao;

    public MovieService(MovieDao movieDao, CrossedDao crossedDao) {
        this.movieDao = movieDao;
        this.crossedDao = crossedDao;
    }

    public List<Movie> listMovies(Page page) {
        return movieDao.listMovies(page);
    }

    public int insertMovie(Movie movie) {
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
