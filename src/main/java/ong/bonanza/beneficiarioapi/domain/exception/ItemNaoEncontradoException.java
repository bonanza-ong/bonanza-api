package ong.bonanza.beneficiarioapi.domain.exception;

import java.util.UUID;

import ong.bonanza.beneficiarioapi.domain.entity.Item;

public class ItemNaoEncontradoException extends RecursoNaoEncontradoException {

    private ItemNaoEncontradoException(String consulta) {
        super(Item.class, consulta);
    }

    public static ItemNaoEncontradoException buscaPorId(UUID id) {
        return new ItemNaoEncontradoException(String.format("[id=%s]", id.toString()));
    }

}
