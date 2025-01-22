package com.alura.forohub.service;

import com.alura.forohub.domain.answer.Answer;
import com.alura.forohub.domain.answer.AnswerResponseDTO;
import com.alura.forohub.domain.answer.UpdateAnswerDTO;
import com.alura.forohub.domain.respuesta.AnswerRegisterDTO;
import com.alura.forohub.domain.user.User;
import com.alura.forohub.infra.errores.IntegrityValidation;
import com.alura.forohub.repository.AnswerRepository;
import com.alura.forohub.repository.TopicRepository;
import com.alura.forohub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AnswerService {

    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(TopicRepository topicRepository, UserRepository userRepository, AnswerRepository answerRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public AnswerResponseDTO registrarRespuesta(AnswerRegisterDTO datos) {
        //Primero busco el autor y el topic para ver si existen
        com.alejobeliz.proyectos.forohub.domain.topico.Topic topic = topicRepository.findById(datos.topico_id()).orElseThrow(() -> new IntegrityValidation("No hay ningun tópico con ese id"));
        User autor = userRepository.findById(datos.autor_id()).orElseThrow(() -> new IntegrityValidation("No hay ningun usuario con ese id"));

        Answer answer = new Answer(null, datos.mensaje(), topic, LocalDateTime.now(), autor);

        //Tengo que agregar la answer a la lista del topic
        topic.agregarRespuesta(answer);

        answerRepository.save(answer);

        return new AnswerResponseDTO(answer);

    }

    public Page<AnswerResponseDTO> listarRespuestas(Pageable respuestas) {
        Page page = answerRepository.findAll(respuestas).map(AnswerResponseDTO::new);
        return page;
    }

    public AnswerResponseDTO getRespuestaPorId(Long id) {
        Optional<Answer> respuesta = answerRepository.findById(id);
        if (!respuesta.isPresent()) {
            throw new IntegrityValidation("No hay respuestas que correspondan a ese Id");
        }
        return new AnswerResponseDTO(respuesta.get());
    }

    @Transactional
    public AnswerResponseDTO actualizarRespuesta(Long id, UpdateAnswerDTO datos) {
        Optional<Answer> respuestaBuscada = answerRepository.findById(id);
        if (!respuestaBuscada.isPresent()) {
            throw new IntegrityValidation("No hay respuestas que correspondan a ese Id");
        }
        Answer answer = respuestaBuscada.get();
        answer.actualizarRespuesta(datos);
        answerRepository.save(answer);
        return new AnswerResponseDTO(answer);
    }


}
