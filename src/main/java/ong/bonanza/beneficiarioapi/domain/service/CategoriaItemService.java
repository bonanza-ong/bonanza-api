package ong.bonanza.beneficiarioapi.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.CategoriaItem;
import ong.bonanza.beneficiarioapi.domain.exception.CategoriaItemNaoEncotradaException;
import ong.bonanza.beneficiarioapi.domain.repository.CategoriaItemRepository;

@RequiredArgsConstructor
@Service
public class CategoriaItemService {

    private final CategoriaItemRepository categoriaItemRepository;

    public CategoriaItem buscarPorId(UUID id) {
        return categoriaItemRepository
                .findById(id)
                .orElseThrow(() -> CategoriaItemNaoEncotradaException.buscaPorId(id));
    }

}
