package com.norab.director;

public class Director {
    private Integer actorId;
    private Integer movieId;

    public Director(Integer actorId, Integer movieId) {
        this.actorId = actorId;
        this.movieId = movieId;
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