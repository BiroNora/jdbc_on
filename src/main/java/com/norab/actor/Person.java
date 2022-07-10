package com.norab.actor;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    @Id
    private Integer actorId;
    private String fullName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    @Transient
    private Integer age;

    public Person() {
    }

    public Person(String fullName, LocalDate birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Person(String fullName, LocalDate birthDate, LocalDate deathDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Person(Integer actorId, String fullName, LocalDate birthDate, LocalDate deathDate) {
        this.actorId = actorId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
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
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
            "actorId=" + actorId +
            ", fullName='" + fullName + '\'' +
            ", birthDate=" + birthDate +
            ", deathDate=" + deathDate +
            ", age=" + age +
            '}';
    }
}


