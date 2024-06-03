package ong.bonanza.beneficiarioapi.domain.exception;

import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;

public class DemandaItemInvalidoException extends RecursoInvalidoException {

    public DemandaItemInvalidoException(Atributo... atributos) {
        super(DemandaItem.class, atributos);
    }

}
