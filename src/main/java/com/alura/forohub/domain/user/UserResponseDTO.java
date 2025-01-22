package com.alura.forohub.domain.user;

public record UserResponseDTO(
        Long id,
        String name
) {
    public UserResponseDTO(User data) {
        this( data.getId(),data.getName());
    }
}
