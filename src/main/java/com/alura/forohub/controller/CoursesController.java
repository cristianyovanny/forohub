package com.alura.forohub.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos")
public class CoursesController {

    private final com.alejobeliz.proyectos.forohub.service.CourseService courseService;

    @Autowired
    public CoursesController(com.alejobeliz.proyectos.forohub.service.CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Crear un nuevo curso", description = "Crea un nuevo curso en el sistema.")
    @PostMapping
    public ResponseEntity<com.alejobeliz.proyectos.forohub.domain.curso.CourseResponseDTO> crearCurso(@RequestBody @Valid com.alejobeliz.proyectos.forohub.domain.curso.CourseRegisterDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        com.alejobeliz.proyectos.forohub.domain.curso.CourseResponseDTO response = courseService.registrarCurso(datos);
        URI uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Listar todos los cursos", description = "Devuelve una lista paginada de todos los cursos disponibles.")
    @GetMapping
    public ResponseEntity<Page<com.alejobeliz.proyectos.forohub.domain.curso.CourseResponseDTO>> listarCursos(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<com.alejobeliz.proyectos.forohub.domain.curso.CourseResponseDTO> page = courseService.listarCursos(paginacion);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Obtener un curso por ID", description = "Devuelve un curso basado en el ID proporcionado.")
    @GetMapping("/{id}")
    public ResponseEntity<com.alejobeliz.proyectos.forohub.domain.curso.CourseResponseDTO> obtenerCursoPorId(@PathVariable Long id) {
        com.alejobeliz.proyectos.forohub.domain.curso.CourseResponseDTO response = courseService.getCursoPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar un curso existente", description = "Actualiza la información de un curso existente.")
    @PutMapping("/{id}")
    public ResponseEntity<com.alejobeliz.proyectos.forohub.domain.curso.CourseResponseDTO> actualizarCurso(@PathVariable Long id, @RequestBody @Valid com.alejobeliz.proyectos.forohub.domain.curso.UpdateCourseDTO datos) {
        com.alejobeliz.proyectos.forohub.domain.curso.CourseResponseDTO response = courseService.actualizarCurso(id, datos);
        return ResponseEntity.ok(response);
    }

}
