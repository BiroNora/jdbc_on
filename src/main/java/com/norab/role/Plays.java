package com.norab.role;

import com.cedarsoftware.util.io.JsonWriter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.Map;

public class Plays {
    @Id
    @JsonIgnore
    private int roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public String jsonString() {
        Map<String, Object> conf = Map.of(JsonWriter.SKIP_NULL_FIELDS, true, JsonWriter.TYPE, false);
        return JsonWriter.objectToJson(this, conf);
    }
}
