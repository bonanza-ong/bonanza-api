package ong.bonanza.api.domain.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ong.bonanza.api.domain.entity.CategoriaItem;

public class CategoriaItemComReferenciaCliclicaException extends ReferenciaCiclicaException {

    public CategoriaItemComReferenciaCliclicaException(CategoriaItem principal, CategoriaItem atual) {
        super(CategoriaItem.class, printarCadeiaDeReferencias(principal, atual));
    }

    private static String printarCadeiaDeReferencias(CategoriaItem principal, CategoriaItem atual) {
        return String.join(" (->) ",
                buscarCadeiaDeReferencias(principal, atual, new ArrayList<>()).stream()
                        .map(UUID::toString)
                        .toList());
    }

    private static List<UUID> buscarCadeiaDeReferencias(CategoriaItem principal, CategoriaItem atual,
            List<UUID> idsVisitados) {
        idsVisitados.add(principal.getId());

        if (idsVisitados.contains(atual.getId())) {
            idsVisitados.add(atual.getId());
            return idsVisitados;
        }

        if (principal.getPai() == null)
            return idsVisitados;

        return buscarCadeiaDeReferencias(principal.getPai(), atual, idsVisitados);
    }

}
