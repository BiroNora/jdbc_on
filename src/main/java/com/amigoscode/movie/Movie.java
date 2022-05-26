package com.amigoscode.movie;

import com.amigoscode.actor.Actor;

import java.time.LocalDate;
import java.util.List;

public record Movie(Integer id,
                    String title,
                    LocalDate releaseDate) {

}
