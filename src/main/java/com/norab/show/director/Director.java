package com.norab.show.director;

import com.norab.utils.ToJsonString;

public class Director extends ToJsonString {
    private Integer actorId;
    private Integer movieId;

    public Director(Integer actorId, Integer movieId) {
        this.actorId = actorId;
        this.movieId = movieId;
    }

    public boolean isValid() {
        return actorId != null && movieId != null;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Director{" +
            "actorId=" + actorId +
            ", movieId=" + movieId +
            '}';
    }
}
