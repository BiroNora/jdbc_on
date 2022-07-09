package com.norab.actor;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.time.Period;

public class Actor {
    @Id
    private Long actorId;
    private String fullName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    @Transient
    private Integer age;

    public Actor() {
    }

    public Actor(String fullName, LocalDate birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Actor(String fullName, LocalDate birthDate, LocalDate deathDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Actor(Long actorId, String fullName, LocalDate birthDate, LocalDate deathDate) {
        this.actorId = actorId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public Integer getAge() {
        if (this.deathDate == null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        }
        return Period.between(birthDate, deathDate).getYears();
    }

    @Override
    public String toString() {
        return "Actor{" +
            "actorId=" + actorId +
            ", fullName='" + fullName + '\'' +
            ", birthDate=" + birthDate +
            ", deathDate=" + deathDate +
            ", age=" + getAge() +
            '}';
    }
}


