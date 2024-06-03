package ong.bonanza.beneficiarioapi.domain.exception;

import ong.bonanza.beneficiarioapi.domain.entity.Padrinho;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;

public class PadrinhoJaExistenteException extends RecursoJaExistenteException {

    private PadrinhoJaExistenteException(String identificador) {
        super(Padrinho.class, identificador);
    }

    public static PadrinhoJaExistenteException comPessoa(Pessoa pessoa) {
        return new PadrinhoJaExistenteException(String.format("[pessoaId=%s]", pessoa.getId().toString()));
    }

}
