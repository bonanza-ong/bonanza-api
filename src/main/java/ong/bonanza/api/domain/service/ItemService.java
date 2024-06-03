package ong.bonanza.api.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.Item;
import ong.bonanza.api.domain.exception.ItemNaoEncontradoException;
import ong.bonanza.api.domain.repository.ItemRepository;

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

    public List<Item> buscarPaginado(int page, int size, String nome) {
        return itemRepository.findByNomeContainingIgnoreCase(nome,
                PageRequest.of(
                        page,
                        size,
                        Sort.by("nome").ascending()));
    }

    public List<Item> buscarPaginado(int page, int size) {
        return buscarPaginado(page, size, "");
    }
}
