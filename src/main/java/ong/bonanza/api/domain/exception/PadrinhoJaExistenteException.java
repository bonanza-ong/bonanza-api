package ong.bonanza.api.domain.exception;

import ong.bonanza.api.domain.entity.Padrinho;
import ong.bonanza.api.domain.entity.Pessoa;

public class PadrinhoJaExistenteException extends RecursoJaExistenteException {

    private PadrinhoJaExistenteException(String identificador) {
        super(Padrinho.class, identificador);
    }

    public static PadrinhoJaExistenteException comPessoa(Pessoa pessoa) {
        return new PadrinhoJaExistenteException(String.format("[pessoaId=%s]", pessoa.getId().toString()));
    }

}
