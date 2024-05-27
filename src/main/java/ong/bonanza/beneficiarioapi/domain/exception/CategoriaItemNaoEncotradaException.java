package ong.bonanza.beneficiarioapi.domain.exception;

import java.util.UUID;

import ong.bonanza.beneficiarioapi.domain.entity.CategoriaItem;

public class CategoriaItemNaoEncotradaException extends RecursoNaoEncontradoException {

    private CategoriaItemNaoEncotradaException(String consulta) {
        super(CategoriaItem.class, consulta);
    }

    public static CategoriaItemNaoEncotradaException buscaPorId(UUID id) {
        return new CategoriaItemNaoEncotradaException(String.format("[id=%s]", id.toString()));
    }

}
