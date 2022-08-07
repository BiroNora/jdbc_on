package com.norab.show.genre;

import com.norab.exception.NotFoundException;
import com.norab.show.crossed.SearchLocation;
import com.norab.utils.BooleanResponse;
import com.norab.utils.ResultResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDao.GenresByMovieId> selectGenres() {
        return genreService.selectGenres();
    }

    @GetMapping("/all")
    public List<ResultResponse> selectAllGenre() {
        return genreService.selectAllGenre();
    }

    @GetMapping("/id")
    public BooleanResponse selectGenreById(
        @RequestParam(name = "id") Integer id,
        @RequestParam(name = "genre") String genre) {
        return new BooleanResponse(genreService.selectGenreById(id, genre));
    }

    @GetMapping("/genre/{id}")
    public List<ResultResponse> selectGenresByMovieId(
        @PathVariable("id") Integer movieId) {
        return genreService.selectGenresByMovieId(movieId);
    }

    @GetMapping("/genre")
    public List<GenreDao.MoviesByGenre> selectMoviesByGenre(
        @RequestParam(name = "genre") String genre) {
        return genreService.selectMoviesByGenre(genre);
    }

    @GetMapping("/filmgen")
    List<GenreDao.GenresByMovie> selectGenresByMovieTitle(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "location", required = false, defaultValue = "ALL") SearchLocation location) {
        return genreService.selectGenresByMovieTitle(title, location);
    }

    @PostMapping
    public BooleanResponse insertGenre(@RequestBody Genre genre) {
        return new BooleanResponse(genreService.insertGenre(genre));
    }

    @DeleteMapping("{id}")
    public void deleteGenre(
        @PathVariable("id") Integer movieId,
        @RequestParam(name = "genre") String genre) {
        if (!genreService.deleteGenre(movieId, genre)) {
            throw new NotFoundException("Genre not found");
        }
    }
}
