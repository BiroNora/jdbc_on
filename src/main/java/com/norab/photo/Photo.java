package com.norab.photo;

import org.springframework.data.annotation.Id;

public class Photo {
    @Id
    private Long id;
    private String photoUrl;
    private Long movieId;
    private Long actorId;
    private Long roleId;

    public Photo() {
    }

    public Photo(String photoUrl, Long movieId, Long actorId, Long roleId) {
        this.photoUrl = photoUrl;
        this.movieId = movieId;
        this.actorId = actorId;
        this.roleId = roleId;
    }

    public Photo(Long id, String photoUrl, Long movieId, Long actorId, Long roleId) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.movieId = movieId;
        this.actorId = actorId;
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Photo{" +
            "id=" + id +
            ", photoUrl='" + photoUrl + '\'' +
            ", movieId=" + movieId +
            ", actorId=" + actorId +
            ", roleId=" + roleId +
            '}';
    }
}
