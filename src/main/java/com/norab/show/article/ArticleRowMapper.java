package com.norab.show.article;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            return new Article(
                rs.getInt("art_id"),
                rs.getObject("user_id", java.util.UUID.class),
                rs.getString("body"),
                rs.getInt("rating"),
                rs.getInt("movie_id")
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
