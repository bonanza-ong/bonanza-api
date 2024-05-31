package ong.bonanza.beneficiarioapi.domain.exception;

import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.entity.Usuario;

public class PessoaNaoEncontradaException extends RecursoNaoEncontradoException {

    private PessoaNaoEncontradaException(String consulta) {
        super(Pessoa.class, consulta);
    }

    public static PessoaNaoEncontradaException buscaPorUsuario(Usuario usuario) {
        return new PessoaNaoEncontradaException(String.format("[usuarioId=%s]", usuario.getId().toString()));
    }

}
