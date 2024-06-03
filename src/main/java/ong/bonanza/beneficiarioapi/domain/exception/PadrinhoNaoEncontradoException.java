package ong.bonanza.beneficiarioapi.domain.exception;

import ong.bonanza.beneficiarioapi.domain.entity.Beneficiario;
import ong.bonanza.beneficiarioapi.domain.entity.Padrinho;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;

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
