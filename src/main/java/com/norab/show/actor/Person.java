package com.norab.show.actor;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.norab.utils.ToJsonString;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Person extends ToJsonString {
    @Id
    @JsonIgnore
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

    @JsonIgnore
    public Integer getAge() {
        if (birthDate == null) return null;
        if (deathDate == null) {
            return LocalDate.now().getYear() - birthDate;
        }
        return deathDate - birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (fullName == null || person.fullName == null || !fullName.equals(person.fullName)) return false;
        if (birthDate != null && person.birthDate != null && !birthDate.equals(person.birthDate)) return false;
        return deathDate == null || person.deathDate == null || deathDate.equals(person.deathDate);
    }

    @Override
    public int hashCode() {
        int result = actorId != null ? actorId.hashCode() : 0;
        result = 31 * result + fullName.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + deathDate.hashCode();
        return result;
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
