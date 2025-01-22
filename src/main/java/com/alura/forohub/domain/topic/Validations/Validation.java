package com.alura.forohub.domain.topic.Validations;


import com.alura.forohub.domain.topic.TopicRegisterDTO;

public interface Validation {
    public void validate(TopicRegisterDTO data);
}
