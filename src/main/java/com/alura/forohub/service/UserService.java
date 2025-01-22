package com.alura.forohub.service;

import com.alura.forohub.domain.user.UpdateUserDTO;
import com.alura.forohub.domain.user.User;
import com.alura.forohub.domain.user.UserResponseDTO;
import com.alura.forohub.infra.errores.IntegrityValidation;
import com.alura.forohub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO userRegister() {
        //Verificamos que no este ya creado
        Optional<User> userWanted = userRepository.findByCorreo(data.correo());

        if (userWanted.isPresent()) {
            throw new IntegrityValidation("El correo " + data.correo() + " ya esta registrado");
        }

        String username = data.name();
        String email = data.email();
        String password = data.password();

        //Ahora encriptamos la contraseña
        password = passwordEncoder.encode(password);

        User nuevoUser = new User(null, username, email, password);

        userRepository.save(nuevoUser);

        return new UserResponseDTO(nuevoUser);
    }

    public Page<> listUser(Pageable usuarios) {
        Page page = userRepository.findAll(usuarios).map();
        return page;
    }

    @Transactional
    public UserResponseDTO userUpdate(Long id, UpdateUserDTO data) {
        Optional<User> userWanted = userRepository.findById(id);
        if (!userWanted.isPresent()) {
            throw new IntegrityValidation("No hay usuarios que correspondan a ese Id");
        }
        User user = userWanted.get();
        user.UpdateData(data);
        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    public UserResponseDTO getUsuarioPorId(Long id) {
        Optional<User> usuario = userRepository.findById(id);
        if (!usuario.isPresent()) {
            throw new IntegrityValidation("No hay usuarios que correspondan a ese Id");
        }
        return new UserResponseDTO(user.get());
    }
}

