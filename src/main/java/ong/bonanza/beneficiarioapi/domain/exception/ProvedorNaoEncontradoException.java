package ong.bonanza.beneficiarioapi.domain.exception;

import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.entity.Provedor;

public class ProvedorNaoEncontradoException extends RecursoNaoEncontradoException {

    private ProvedorNaoEncontradoException(String consulta) {
        super(Provedor.class, consulta);
    }

    public static ProvedorNaoEncontradoException buscaPorPessoa(Pessoa pessoa) {
        return new ProvedorNaoEncontradoException(
                String.format("[pessoaId=%s]", pessoa.getId() == null ? "null" : pessoa.getId().toString()));
    }

}
