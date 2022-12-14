package com.norab.show.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.norab.utils.ToJsonString;
import org.springframework.data.annotation.Id;

public class Plays extends ToJsonString {
    @Id
    @JsonIgnore
    private Integer roleId;
    private String roleName;
    private Integer movieId;
    private Integer actorId;

    public Plays() {
    }

    public Plays(String roleName, Integer movieId, Integer actorId) {
        this.roleName = roleName;
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public Plays(int roleId, String roleName, Integer movieId, Integer actorId) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public boolean isValid() {
        return movieId != null && movieId > 0 && actorId != null && actorId > 0;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
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
