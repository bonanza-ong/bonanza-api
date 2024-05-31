package ong.bonanza.beneficiarioapi.domain.exception;

import java.util.UUID;

import ong.bonanza.beneficiarioapi.domain.entity.Usuario;

public class UsuarioNaoEncontradoException extends RecursoNaoEncontradoException {

    private UsuarioNaoEncontradoException(String consulta) {
        super(Usuario.class, consulta);
    }

    public static UsuarioNaoEncontradoException buscaPorId(UUID id) {
        return new UsuarioNaoEncontradoException(String.format("[id=%s]", id.toString()));
    }

}
