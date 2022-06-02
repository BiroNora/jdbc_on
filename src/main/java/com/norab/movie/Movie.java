package com.norab.movie;

import java.time.LocalDate;

public record Movie (Long id,
                    String title,
                    LocalDate releaseDate) {

}
