package com.alejobeliz.proyectos.forohub.domain.topico;



import com.alura.forohub.domain.courses.CourseResponseDTO;
import com.alura.forohub.domain.topic.Status;
import com.alura.forohub.domain.topic.Topic;
import com.alura.forohub.domain.user.UserResponseDTO;

import java.time.LocalDate;

public record LongTopicResponseDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        Status status,
        UserResponseDTO autor,
        CourseResponseDTO curso
) {

    public LongTopicResponseDTO(Topic datos){
        this(
                datos.getId(),
                datos.getTitulo(),
                datos.getMensaje(),
                datos.getFechaCreacion(),
                datos.getStatus(),
                new UserResponseDTO(data.getAutor()),
                new CourseResponseDTO(data.getCourse())
        );

    }

}
