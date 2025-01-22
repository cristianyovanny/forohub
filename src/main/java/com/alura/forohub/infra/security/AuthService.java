package com.alura.forohub.infra.security;

import com.alejobeliz.proyectos.forohub.repository.UsuarioRepository;
import com.alura.forohub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio de autenticación que implementa {@link UserDetailsService}.
 * Carga los detalles del usuario para la autenticación basada en el correo electrónico.
 */
@Service
public class AuthService implements UserDetailsService {//Aca empieza la logica para hacer login y aplicar seguridad a mi app

    private UserRepository userRepository;

    /**
     * Constructor de la clase {@link com.alejobeliz.proyectos.forohub.infra.security.AuthService}.
     *
     * @param userRepository El repositorio de usuarios que proporciona acceso a los datos del usuario.
     */
    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Carga los detalles del usuario basado en el nombre de usuario (correo electrónico).
     *
     * @param username El correo electrónico del usuario que se está cargando.
     * @return Un {@link UserDetails} que contiene la información del usuario.
     * @throws UsernameNotFoundException Si el usuario no es encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

}
