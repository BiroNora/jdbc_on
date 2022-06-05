package com.norab.actor;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.time.Period;

public class Actor {
    @Id
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    @Transient
    private Integer age;

    public Actor() {
    }

    public Actor(String fullName, LocalDate birthDate, LocalDate deathDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Actor(Long id, String fullName, LocalDate birthDate, LocalDate deathDate) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Actor{" +
            "id=" + id +
            ", fullName='" + fullName + '\'' +
            ", birthDate=" + birthDate +
            ", deathDate=" + deathDate +
            ", age=" + getAge() +
            '}';
    }
}


