package ong.bonanza.api.domain.exception;

import java.util.UUID;

import ong.bonanza.api.domain.entity.CategoriaItem;

public class CategoriaItemNaoEncotradaException extends RecursoNaoEncontradoException {

    private CategoriaItemNaoEncotradaException(String consulta) {
        super(CategoriaItem.class, consulta);
    }

    public static CategoriaItemNaoEncotradaException buscaPorId(UUID id) {
        return new CategoriaItemNaoEncotradaException(String.format("[id=%s]", id.toString()));
    }

}
