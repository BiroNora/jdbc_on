package com.amigoscode.actor;

import java.time.LocalDate;

public record Actor(Integer id, String fullName, LocalDate birthdate) {
}
