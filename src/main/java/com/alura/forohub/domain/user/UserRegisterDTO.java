package com.alura.forohub.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO(
        @NotBlank String nombre,
        @NotBlank String correo,
        @NotBlank String contrasena
) {
}
