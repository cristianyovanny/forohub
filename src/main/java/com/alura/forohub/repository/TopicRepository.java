package com.alura.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<com.alejobeliz.proyectos.forohub.domain.topico.Topic,Long> {


    @Query("SELECT t FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
    Optional<com.alejobeliz.proyectos.forohub.domain.topico.Topic> findByTituloAndMensaje(String titulo, String mensaje);

    Optional<com.alejobeliz.proyectos.forohub.domain.topico.Topic> findById(Long id);

}
