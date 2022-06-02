package com.norab.actor;

import java.time.LocalDate;

public record Actor (Long id,
                     String fullName,
                     LocalDate birthDate,
                     LocalDate deathDate) {
}
