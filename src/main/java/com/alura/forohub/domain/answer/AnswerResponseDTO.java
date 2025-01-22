package com.alura.forohub.domain.answer;

import java.time.LocalDateTime;

public record AnswerResponseDTO(
        Long id,
        String mensaje,
        Long topico_id,
        LocalDateTime fechaCreacion,
        Long autor_id
) {
    public AnswerResponseDTO(Respuesta respuesta) {
        this(respuesta.getId(),respuesta.getMensaje(),respuesta.getTopico().getId(), LocalDateTime.now(),respuesta.getAutor().getId());
    }
}
