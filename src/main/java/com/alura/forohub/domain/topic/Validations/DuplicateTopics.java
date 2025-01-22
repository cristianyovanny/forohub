package com.alura.forohub.domain.topic.Validations;


import com.alura.forohub.domain.topic.Topic;
import com.alura.forohub.domain.topic.TopicRegisterDTO;
import com.alura.forohub.repository.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicateTopics implements Validation{

    private TopicRepository repository;

    @Autowired
    public DuplicateTopics(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(TopicRegisterDTO datos) {
        Optional<Topic> topico =repository.findByTituloAndMensaje(data.title(),data.message());
        if(topico.isPresent()){
            throw new ValidationException("Ya existe un mismo tópico con ese título y mensaje");
        }
    }

}
