package ong.bonanza.beneficiarioapi.application.usecase;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Item;
import ong.bonanza.beneficiarioapi.domain.service.ItemService;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BuscarItemPaginadoUC {
    private final ItemService itemService;
    private final BuscarItensPaginadoUCMapper mapper;
    
    public List<ItemDTO> executar(int page, int size, String nome) {
        return mapper.toListItemDTO(itemService.buscarPaginado(page, size, nome));
    }
    
    public record ItemDTO(
            UUID id, 
            String nome,
            UUID categoriaPrincipalId,
            List<UUID> categoriasId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }
    
    @Mapper
    public interface BuscarItensPaginadoUCMapper {
        List<ItemDTO> toListItemDTO(List<Item> listItem);
    }
}
