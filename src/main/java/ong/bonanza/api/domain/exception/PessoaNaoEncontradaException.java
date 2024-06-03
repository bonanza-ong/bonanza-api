package ong.bonanza.api.domain.exception;

import ong.bonanza.api.domain.entity.Pessoa;
import ong.bonanza.api.domain.entity.Usuario;

public class PessoaNaoEncontradaException extends RecursoNaoEncontradoException {

    private PessoaNaoEncontradaException(String consulta) {
        super(Pessoa.class, consulta);
    }

    public static PessoaNaoEncontradaException buscaPorUsuario(Usuario usuario) {
        return new PessoaNaoEncontradaException(String.format("[usuarioId=%s]", usuario.getId().toString()));
    }

}
