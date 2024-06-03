package ong.bonanza.api.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.application.exception.ForbiddenException;
import ong.bonanza.api.application.service.AuthService;
import ong.bonanza.api.domain.entity.Beneficiario;
import ong.bonanza.api.domain.entity.DemandaItem;
import ong.bonanza.api.domain.entity.Item;
import ong.bonanza.api.domain.entity.Usuario;
import ong.bonanza.api.domain.enumeration.StatusDemandaItem;
import ong.bonanza.api.domain.service.BeneficiarioService;
import ong.bonanza.api.domain.service.DemandaItemService;
import ong.bonanza.api.domain.service.ItemService;
import ong.bonanza.api.domain.service.UsuarioService;

@RequiredArgsConstructor
@Component
public class CadastrarDemandaItemUC {

    private final AuthService authService;

    private final UsuarioService usuarioService;

    private final BeneficiarioService beneficiarioService;

    private final ItemService itemService;

    private final DemandaItemService demandaItemService;

    private final CadastrarDemandaItemUCMapper mapper;

    public DemandaItemDTO executar(NovaDemandaItemDTO novaDemandaItem) {

        if (!(authService.possuiAlgumaRole("administrador")
                || novaDemandaItem.solicitanteId.equals(authService.idUsuarioAutenticado())))
            throw new ForbiddenException("cadastrar demanda por outro usuário");

        final Beneficiario beneficiario = beneficiarioService.buscarPorId(novaDemandaItem.beneficiarioId);

        return mapper.toDemandaItemDTO(demandaItemService.cadastrar(
                mapper.toDemandaItem(
                        beneficiario,
                        usuarioService.buscarPorId(novaDemandaItem.solicitanteId),
                        itemService.buscarPorId(novaDemandaItem.informacoesItem.itemId),
                        novaDemandaItem)));

    }

    public record DemandaItemDTO(
            UUID id,
            UUID usuarioSolicitanteId,
            UUID beneficiarioId,
            StatusDemandaItem status,
            InformacoesItemDemandaItemDTO informacoesItem) {
    }

    public record NovaDemandaItemDTO(
            UUID solicitanteId,
            UUID beneficiarioId,
            InformacoesItemDemandaItemDTO informacoesItem) {
    }

    public record InformacoesItemDemandaItemDTO(
            UUID itemId,
            Integer quantidadeSolicitada,
            String detalhes) {
    }

    @Mapper
    public interface CadastrarDemandaItemUCMapper {

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "versionNum", ignore = true)
        @Mapping(target = "quantidadeAtendida", ignore = true)
        @Mapping(target = "aprovador", ignore = true)
        @Mapping(target = "status", ignore = true)
        @Mapping(target = "quantidadeSolicitada", source = "novaDemandaItemDTO.informacoesItem.quantidadeSolicitada")
        @Mapping(target = "detalhes", source = "novaDemandaItemDTO.informacoesItem.detalhes")
        DemandaItem toDemandaItem(
                Beneficiario beneficiario,
                Usuario solicitante,
                Item item,
                NovaDemandaItemDTO novaDemandaItemDTO);

        @Mapping(target = "usuarioSolicitanteId", source = "solicitante.id")
        @Mapping(target = "beneficiarioId", source = "beneficiario.id")
        @Mapping(target = "informacoesItem", source = "demandaItem")
        DemandaItemDTO toDemandaItemDTO(DemandaItem demandaItem);

        @Mapping(target = "itemId", source = "item.id")
        InformacoesItemDemandaItemDTO toInformacoesItemDemandaItemDTO(DemandaItem demandaItem);

    }

}
