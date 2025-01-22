package com.alura.forohub.controller;

import com.alura.forohub.domain.user.UpdateUserDTO;
import com.alura.forohub.domain.user.UserRegisterDTO;
import com.alura.forohub.domain.user.UserResponseDTO;
import com.alura.forohub.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Crea un nuevo usuario en el sistema.")
    @PostMapping
    public ResponseEntity<UserResponseDTO> crearUsuario(@RequestBody @Valid UserRegisterDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        UserResponseDTO response = service.userRegister(data);
        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista paginada de todos los usuarios registrados.")
    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> listarUsuarios(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable paginacion) {
        Page page = service.listUser(paginacion);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve un usuario basado en el ID proporcionado.")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        UserResponseDTO response = service.getUsuarioPorId(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar un usuario existente", description = "Actualiza la información de un usuario existente.")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> actualizarUsuario(@PathVariable Long id, @RequestBody @Valid UpdateUserDTO datos) {
        UserResponseDTO response = service.actualizarUsuario(id, datos);
        return ResponseEntity.ok(response);
    }

}
