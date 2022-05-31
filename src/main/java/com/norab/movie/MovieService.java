package com.norab.movie;

import com.norab.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieDao movieDao;

    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public List<Movie> getMovies() {
        return movieDao.selectMovies();
    }

    public void addNewMovie(Movie movie) {
        String movieTitle = movie.title();
        List<Movie> movies = movieDao.selectMovies();
        List<Movie> collect = movies.stream()
            .filter(x -> x.title().equals(movieTitle)).toList();
        if (collect.size() != 0) {
            throw new IllegalStateException("this movie already exists");
        }
        int result = movieDao.insertMovie(movie);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }

    public void deleteMovie(Integer id) {
        Optional<Movie> movie1 = movieDao.selectMovieById(id);
        movie1.ifPresentOrElse(movie -> {
            int result = movieDao.deleteMovie(id);
            if (result != 1) {
                throw new IllegalStateException("oops could not delete movie");
            }
        }, () -> {
            throw new NotFoundException(String.format("Movie with id %s not found", id));
        });
    }

    public Movie getMovie(int id) {
        try {
            return (Movie) movieDao.selectMovieById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Movie with id %s not found", id)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMovie(int id, Movie movie) {
        if (movieDao.selectMovieById(id).isPresent()) {
            Movie movie1 = new Movie(id, movie.title(), movie.releaseDate(), movie.picture());
            movieDao.updateMovie(id, movie1);
        } else {
            throw new NotFoundException(String.format("Movie with id %s not found", id));
        }
    }

}
