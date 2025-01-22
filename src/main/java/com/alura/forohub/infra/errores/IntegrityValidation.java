package com.alura.forohub.infra.errores;

public class IntegrityValidation extends RuntimeException {

    public IntegrityValidation(String mensaje) {
        super(mensaje);
    }
}

