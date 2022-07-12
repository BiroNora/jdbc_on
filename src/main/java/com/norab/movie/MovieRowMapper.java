package com.norab.movie;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            return new Movie(
                resultSet.getInt("movie_id"),
                resultSet.getString("title"),
                resultSet.getString("title_original"),
                resultSet.getShort("release_date"),
                resultSet.getBoolean("movie_film")
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
