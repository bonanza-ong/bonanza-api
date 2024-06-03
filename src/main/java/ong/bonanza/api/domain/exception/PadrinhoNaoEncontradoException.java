package ong.bonanza.api.domain.exception;

import ong.bonanza.api.domain.entity.Beneficiario;
import ong.bonanza.api.domain.entity.Padrinho;
import ong.bonanza.api.domain.entity.Pessoa;

public class PadrinhoNaoEncontradoException extends RecursoNaoEncontradoException {

    private PadrinhoNaoEncontradoException(String consulta) {
        super(Padrinho.class, consulta);
    }

    public static PadrinhoNaoEncontradoException buscaPorPessoaEBeneficiario(Pessoa pessoa, Beneficiario beneficiario) {
        return new PadrinhoNaoEncontradoException(
                String.format("[pessoaId=%s e beneficiarioId=%s]",
                        pessoa.getId().toString(),
                        beneficiario.getId().toString()));
    }

}
