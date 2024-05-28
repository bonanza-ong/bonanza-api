package ong.bonanza.beneficiarioapi.domain.exception;

import java.util.UUID;

import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;

public class PessoaNaoEncontradaException extends RecursoNaoEncontradoException {

    private PessoaNaoEncontradaException(String consulta) {
        super(Pessoa.class, consulta);
    }

    public static PessoaNaoEncontradaException buscaPorId(UUID id) {
        return new PessoaNaoEncontradaException(String.format("[id=%s]", id.toString()));
    }

}
