package com.alura.forohub.domain.courses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRegisterDTO(
        @NotBlank
        String nombre,
        @NotNull
        Category category
) {
}
