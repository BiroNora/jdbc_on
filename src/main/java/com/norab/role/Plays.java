package com.norab.role;

import org.springframework.data.annotation.Id;

public class Plays {
    @Id
    private long roleId;
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

    public Plays(long roleId, String roleName, Long movieId, Long actorId) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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
            "roleId=" + roleId +
            ", roleName='" + roleName + '\'' +
            ", movieId=" + movieId +
            ", actorId=" + actorId +
            '}';
    }
}
