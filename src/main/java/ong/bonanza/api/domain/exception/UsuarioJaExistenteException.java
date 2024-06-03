package ong.bonanza.api.domain.exception;

import java.util.UUID;

import ong.bonanza.api.domain.entity.Usuario;

public class UsuarioJaExistenteException extends RecursoJaExistenteException {

    private UsuarioJaExistenteException(String identificador) {
        super(Usuario.class, identificador);
    }

    public static UsuarioJaExistenteException comId(UUID id) {
        return new UsuarioJaExistenteException(String.format("[id=%s]", id.toString()));
    }

}
