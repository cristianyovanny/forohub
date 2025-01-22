package com.alura.forohub.domain.topic;

import java.time.LocalDate;

public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        LocalDate creationDate
) {

    public TopicResponseDTO(Topic datos){
        this(
                datos.getId(),
                datos.getTitle(),
                datos.getMessage(),
                datos.getcreationDate()
        );

    }

}
