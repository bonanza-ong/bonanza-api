package ong.bonanza.beneficiarioapi.domain.exception;

import java.util.UUID;

import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;

public class DemandaItemNaoEncontradaException extends RecursoNaoEncontradoException {

    private DemandaItemNaoEncontradaException(String consulta) {
        super(DemandaItem.class, consulta);
    }

    public static DemandaItemNaoEncontradaException buscaPorId(UUID id) {
        return new DemandaItemNaoEncontradaException(String.format("[id=%s]", id.toString()));
    }

}
