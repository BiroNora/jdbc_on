package com.norab.photo;

import org.springframework.data.annotation.Id;

public class Photo {
    @Id
    private Integer photoId;
    private String photoUrl;
    private Integer movieId;
    private Integer actorId;
    private Integer roleId;

    public Photo() {
    }

    public Photo(String photoUrl, Integer movieId, Integer actorId, Integer roleId) {
        this.photoUrl = photoUrl;
        this.movieId = movieId;
        this.actorId = actorId;
        this.roleId = roleId;
    }

    public Photo(Integer photoId, String photoUrl, Integer movieId, Integer actorId, Integer roleId) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
        this.movieId = movieId;
        this.actorId = actorId;
        this.roleId = roleId;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Photo{" +
            "photoId=" + photoId +
            ", photoUrl='" + photoUrl + '\'' +
            ", movieId=" + movieId +
            ", actorId=" + actorId +
            ", roleId=" + roleId +
            '}';
    }
}
