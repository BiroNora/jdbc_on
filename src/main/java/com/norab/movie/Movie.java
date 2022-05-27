package com.norab.movie;

import java.time.LocalDate;

public record Movie(Integer id,
                    String title,
                    LocalDate releaseDate,
                    String picture) {

}
