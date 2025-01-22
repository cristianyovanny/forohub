package com.alura.forohub.domain.courses;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Curso")
@Table(name = "cursos")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Course(RegistroCursoDTO data) {
        this.id = null;
        this.nombre = data.nombre();
        this.category = data.category();
    }

    public Course(RespuestaCursoDTO datos) {
        this.id=datos.id();
        this.nombre = datos.nombre();
        this.category = datos.category();
    }



    public void actualizarCurso(ActualizarCursoDTO curso) {
        if (curso.nombre() != null) {
            this.nombre = curso.nombre();
        }
        if (curso.categoria() != null) {
            this.categoria = curso.categoria();
        }
    }
}
