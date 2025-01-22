package com.alura.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegisterDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long author_id,
        @NotNull
        Long course_id
) {

}
