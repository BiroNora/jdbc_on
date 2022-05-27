package com.norab.movie;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Movie(
            resultSet.getInt("movie_id"),
            resultSet.getString("title"),
            LocalDate.parse(resultSet.getString("release_date")),
            resultSet.getString("picture")
        );
    }
}
