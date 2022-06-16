package com.norab.role;

import org.springframework.data.annotation.Id;

public class Plays {
    @Id
    private long Id;
    private String roleName;
    private Long movieId;
    private Long actorId;

    public Plays() {
    }

    public Plays(String roleName, Long movieId, Long actorId) {
        this.roleName = roleName;
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public Plays(long id, String roleName, Long movieId, Long actorId) {
        Id = id;
        this.roleName = roleName;
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    @Override
    public String toString() {
        return "Plays{" +
            "Id=" + Id +
            ", roleName='" + roleName + '\'' +
            ", movieId=" + movieId +
            ", actorId=" + actorId +
            '}';
    }
}
