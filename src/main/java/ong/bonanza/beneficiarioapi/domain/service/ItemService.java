package ong.bonanza.beneficiarioapi.domain.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Item;
import ong.bonanza.beneficiarioapi.domain.repository.ItemRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Item cadastrar(Item item) {
        return itemRepository.save(item);
    }
    
    public List<Item> buscarPaginado(int page, int size, String nome) {
        if (nome.isBlank()) {
            return itemRepository.findAll(
                            PageRequest.of(
                                    page,
                                    size,
                                    Sort.by("updatedAt").descending()))
                    .toList();
        } else {
            return itemRepository.findByNomeContaining(nome,
                    PageRequest.of(
                            page,
                            size,
                            Sort.by("updatedAt").descending()));
        }
    }
    
    public List<Item> buscarPaginado(int page, int size) {
        return buscarPaginado(page, size, "");
    }
}
