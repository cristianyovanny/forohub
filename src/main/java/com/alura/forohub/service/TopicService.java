package com.alura.forohub.service;

import com.alura.forohub.domain.courses.Course;
import com.alura.forohub.domain.user.User;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private List<Validations> validations;

    @Autowired
    public TopicService(List<Validations> validaciones, TopicoRepository topicRepository, UsuarioRepository usuarioRepository, CursoRepository curso) {
        this.validations = validations;
        this.topicRepository = topicRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = curso;
    }

    @Transactional
    public RespuestTopicoDTO registrarTopico(com.alejobeliz.proyectos.forohub.domain.topico.TopicResponseDTO datos) {
        Optional<User> usuario = userRepository.findById(datos.autor_id());
        Optional<Course> curso = courseRepository.findById(datos.curso_id());

        if (!usuario.isPresent()) {
            throw new ValidationException("User inexistente");
        }

        if (!curso.isPresent()) {
            throw new ValidationException("Course inexistente");
        }

        var usuarioEncontrado = usuario.get();
        var cursoEncontrado = curso.get();

        validations.forEach(validacion -> validacion.validar(datos));
        Topico topico = new Topico(datos, usuarioEncontrado, cursoEncontrado);
        topicRepository.save(topico);
        RespuestTopicoDTO response = new RespuestTopicoDTO(topico);
        return response;
    }

    public Page<RespuestaTopicoExtensaDTO> listaTopicos(Pageable topicos) {
        Page page = topicRepository.findAll(topicos).map(RespuestaTopicoExtensaDTO::new);
        return page;
    }


    public RespuestaTopicoExtensaDTO getTopicoPorId(Long id) {
        Optional<Topico> topico = topicRepository.findById(id);
        if (!topico.isPresent()) {
            throw new com.alejobeliz.proyectos.forohub.infra.errores.IntegrityValidation("No hay tópico que correspondan a ese Id");
        }
        return new RespuestaTopicoExtensaDTO (topico.get());
    }

    @Transactional
    public RespuestaTopicoExtensaDTO actualizarTopico(Long id, ActualizacionTopicoDTO datos) {
        Optional<Topico> topicoBuscado = topicRepository.findById(id);
        if (!topicoBuscado.isPresent()) {
            throw new com.alejobeliz.proyectos.forohub.infra.errores.IntegrityValidation("No hay topicos que correspondan a ese Id");
        }
        Topico topico = topicoBuscado.get();
        topico.actualizarDatos(datos);
        topicRepository.save(topico);
        return new RespuestaTopicoExtensaDTO(topico);
    }

    @Transactional
    public void eliminarTopico(Long id) {
        Optional<Topico> topicoBuscado = topicRepository.findById(id);
        if (!topicoBuscado.isPresent()) {
            throw new com.alejobeliz.proyectos.forohub.infra.errores.IntegrityValidation("No hay topicos que correspondan a ese Id");
        }
        Topico topico = topicoBuscado.get();
        topicRepository.delete(topico);
    }
}
