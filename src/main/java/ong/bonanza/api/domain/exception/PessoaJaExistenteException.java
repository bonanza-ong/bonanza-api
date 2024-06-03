package ong.bonanza.api.domain.exception;

import ong.bonanza.api.domain.entity.Pessoa;
import ong.bonanza.api.domain.entity.Usuario;

public class PessoaJaExistenteException extends RecursoJaExistenteException {

    private PessoaJaExistenteException(String identificador) {
        super(Pessoa.class, identificador);
    }

    public static PessoaJaExistenteException comUsuario(Usuario pessoa) {
        return new PessoaJaExistenteException(String.format("[usuarioId=%s]", pessoa.getId().toString()));
    }

}
