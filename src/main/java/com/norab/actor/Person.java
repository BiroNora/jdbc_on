package com.norab.actor;


import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    @Id
    private Integer actorId;
    private String fullName;
    private Short birthDate;
    private Short deathDate;

    public Person() {
    }

    public Person(String fullName, Short birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Person(String fullName, Short birthDate, Short deathDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Person(Integer actorId, String fullName, Short birthDate, Short deathDate) {
        this.actorId = actorId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Integer getActorId() {
        return actorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Short getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Short birthDate) {
        this.birthDate = birthDate;
    }

    public Short getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Short deathDate) {
        this.deathDate = deathDate;
    }

    public Integer getAge() {
        if (deathDate == null) {
            return LocalDate.now().getYear() - birthDate;
        }
        return deathDate - birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
            "actorId=" + actorId +
            ", fullName='" + fullName + '\'' +
            ", birthDate=" + birthDate +
            ", deathDate=" + deathDate +
            ", age=" + getAge() +
            '}';
    }
}
