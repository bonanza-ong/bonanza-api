package ong.bonanza.beneficiarioapi.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;
import ong.bonanza.beneficiarioapi.domain.exception.DemandaItemNaoEncontradaException;
import ong.bonanza.beneficiarioapi.domain.repository.DemandaItemRepository;

@RequiredArgsConstructor
@Service
public class DemandaItemService {

    private final DemandaItemRepository demandaItemRepository;

    public DemandaItem buscarPorId(UUID id) {
        return demandaItemRepository
                .findById(id)
                .orElseThrow(() -> DemandaItemNaoEncontradaException.buscaPorId(id));
    }

}
