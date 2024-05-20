package dev.flb.domain;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record User(String id, String name, LocalDate birthDate) {
}
