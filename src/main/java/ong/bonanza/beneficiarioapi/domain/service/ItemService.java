package ong.bonanza.beneficiarioapi.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Item;
import ong.bonanza.beneficiarioapi.domain.exception.ItemNaoEncontradoException;
import ong.bonanza.beneficiarioapi.domain.repository.ItemRepository;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Item cadastrar(Item item) {
        return itemRepository.save(item);
    }

    public Item buscarPorId(UUID id) {
        return itemRepository
                .findById(id)
                .orElseThrow(() -> ItemNaoEncontradoException.buscaPorId(id));
    }

}
