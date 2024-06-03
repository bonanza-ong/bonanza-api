package ong.bonanza.api.application.usecase;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.CategoriaItem;
import ong.bonanza.api.domain.entity.Item;
import ong.bonanza.api.domain.service.CategoriaItemService;
import ong.bonanza.api.domain.service.ItemService;

@RequiredArgsConstructor
@Component
public class CadastrarItemUC {

    private final CadastrarItemUCMapper mapper;

    private final CategoriaItemService categoriaItemService;

    private final ItemService itemService;

    public ItemDTO executar(NovoItemDTO novoItem) {

        final CategoriaItem categoriaPrincipal = categoriaItemService.buscarPorId(novoItem.categoriaId);

        return mapper.toItemDTO(
                itemService.cadastrar(
                        mapper.toItem(
                                novoItem,
                                categoriaPrincipal,
                                categoriaItemService
                                        .obterTodosOsPaisEEu(categoriaItemService.buscarPorId(novoItem.categoriaId)))));

    }

    public record NovoItemDTO(String nome, UUID categoriaId) {
    }

    public record ItemDTO(UUID id, String nome, CategoriaItemDTO categoriaPrincipal) {
    }

    public record CategoriaItemDTO(UUID id, String nome, CategoriaItemDTO pai) {
    }

    @Mapper
    public interface CadastrarItemUCMapper {

        @Mapping(target = "nome", source = "novoItemDTO.nome")
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        Item toItem(NovoItemDTO novoItemDTO, CategoriaItem categoriaPrincipal, List<CategoriaItem> categorias);

        ItemDTO toItemDTO(Item item);

    }

}
