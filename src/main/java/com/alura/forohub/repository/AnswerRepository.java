package com.alura.forohub.repository;

import com.alura.forohub.domain.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer,Long> {

    Optional<Answer> findById(Long id);

}
