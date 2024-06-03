package ong.bonanza.api.domain.exception;

import ong.bonanza.api.domain.entity.DemandaItem;

public class DemandaItemInvalidoException extends RecursoInvalidoException {

    public DemandaItemInvalidoException(Atributo... atributos) {
        super(DemandaItem.class, atributos);
    }

}
