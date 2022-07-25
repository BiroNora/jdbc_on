package com.norab.photo;

import com.cedarsoftware.util.io.JsonWriter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.Map;
import java.util.Objects;

public class Photo {
    @Id
    @JsonIgnore
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

    public boolean isValid1() {
        return !(photoUrl == null ||
            photoUrl.isBlank() ||
            (movieId == null && actorId == null && roleId == null));
    }

    public boolean isValid() {
        return !(photoUrl == null || photoUrl.isBlank() ||
            (movieId == null && actorId == null && roleId == null) ||
            !((movieId == null || movieId > 0) &&
                (actorId == null || actorId > 0) &&
                (roleId == null || roleId > 0))
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return photoId.equals(photo.photoId) && photoUrl.equals(photo.photoUrl) && movieId.equals(photo.movieId) && actorId.equals(photo.actorId) && roleId.equals(photo.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoId, photoUrl, movieId, actorId, roleId);
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

    public String jsonString() {
        Map<String, Object> conf = Map.of(JsonWriter.SKIP_NULL_FIELDS, true, JsonWriter.TYPE, false);
        return JsonWriter.objectToJson(this, conf);
    }
}
