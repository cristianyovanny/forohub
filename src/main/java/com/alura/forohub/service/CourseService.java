package com.alura.forohub.service;

import com.alura.forohub.domain.courses.Course;
import com.alura.forohub.domain.courses.CourseRegisterDTO;
import com.alura.forohub.domain.courses.CourseResponseDTO;
import com.alura.forohub.domain.courses.UpdateCourseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseService courseService;

    @Transactional
    public CourseResponseDTO registrarCurso(CourseRegisterDTO datos) {
        Optional<Course> courseWanted = courseRepository.findByNameAndCategory(data.name(),data.category());
        if(courseWanted.isPresent()){
            throw new com.alejobeliz.proyectos.forohub.infra.errores.IntegrityValidation("Ya existe un curso con ese nombre en esa categoria");
        }
        Course nuevoCourse = new Course(data);
        courseRepository.save(nuevoCourse);
        CourseResponseDTO response = new CourseResponseDTO(nuevoCourse);
        return response;
    }

    @Transactional
    public Page<CourseResponseDTO> listarCursos(Pageable paginacion) {
        return courseRepository.findAll(paginacion).map(CourseResponseDTO::new);
    }

    public CourseResponseDTO getCursoPorId(Long id) {
        Optional<Course> curso  = courseRepository.findById(id);
        if(!curso.isPresent()){
            throw new RuntimeException("Course no encontrado");
        }
        return new CourseResponseDTO(course.get());
    }

    @Transactional
    public CourseResponseDTO actualizarCurso(Long id, UpdateCourseDTO datos) {
        Optional<Course> courseWanted = courseRepository.findById(id);
        if (!courseWanted.isPresent()) {
            throw new com.alejobeliz.proyectos.forohub.infra.errores.IntegrityValidation("No hay cursos que correspondan a ese Id");
        }
        Course course = courseWanted.get();
        course.actualizarCurso(datos);
        courseRepository.save(course);
        return new CourseResponseDTO(course);
    }
}
