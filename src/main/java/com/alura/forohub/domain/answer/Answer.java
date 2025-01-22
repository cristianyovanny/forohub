package com.alura.forohub.domain.answer;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name="Answer")
@Table(name = "Answers")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private com.alejobeliz.proyectos.forohub.domain.topico.Topic topic;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private com.alejobeliz.proyectos.forohub.domain.usuario.User autor;

    public void actualizarRespuesta(UpdateAnswerDTO datos) {
        if(datos.mensaje()!=null){
            this.mensaje=datos.mensaje();
        }
    }

}
