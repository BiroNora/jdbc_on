package com.norab.photo;

import org.springframework.data.annotation.Id;

public class Photo {
    @Id
    private Long photoId;
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

    public Photo(Long photoId, String photoUrl, Long movieId, Long actorId, Long roleId) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
        this.movieId = movieId;
        this.actorId = actorId;
        this.roleId = roleId;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
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
            "photoId=" + photoId +
            ", photoUrl='" + photoUrl + '\'' +
            ", movieId=" + movieId +
            ", actorId=" + actorId +
            ", roleId=" + roleId +
            '}';
    }
}
