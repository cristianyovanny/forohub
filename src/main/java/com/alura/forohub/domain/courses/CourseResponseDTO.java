package com.alura.forohub.domain.courses;

public record CourseResponseDTO(
        Long id,
        String nombre,
        Category category
) {
    public CourseResponseDTO(Course course) {
        this(course.getId(),course.getNombre(),course.getCategory());
    }
}
