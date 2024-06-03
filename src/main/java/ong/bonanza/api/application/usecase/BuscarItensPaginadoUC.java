package ong.bonanza.api.application.usecase;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.Item;
import ong.bonanza.api.domain.service.ItemService;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuscarItensPaginadoUC {

    private final ItemService itemService;

    private final BuscarItensPaginadoUCMapper mapper;

    public List<ItemDTO> executar(int page, int size, String nome) {
        return mapper.toListItemDTO(itemService.buscarPaginado(page, size, nome));
    }

    public record ItemDTO(
            UUID id,
            String nome,
            CategoriaItemDTO categoriaPrincipal,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
    }

    public record CategoriaItemDTO(
            UUID id,
            String nome,
            CategoriaItemDTO pai) {
    }

    @Mapper
    public interface BuscarItensPaginadoUCMapper {
        List<ItemDTO> toListItemDTO(List<Item> listItem);
    }
}
