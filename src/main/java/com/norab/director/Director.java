package com.norab.director;

import com.cedarsoftware.util.io.JsonWriter;

import java.util.Map;

public class Director {
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

    public String jsonString() {
        Map<String, Object> conf = Map.of(JsonWriter.SKIP_NULL_FIELDS, true, JsonWriter.TYPE, false);
        return JsonWriter.objectToJson(this, conf);
    }
}
