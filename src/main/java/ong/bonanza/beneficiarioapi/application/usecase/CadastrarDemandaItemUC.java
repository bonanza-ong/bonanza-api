package ong.bonanza.beneficiarioapi.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Beneficiario;
import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;
import ong.bonanza.beneficiarioapi.domain.entity.Item;
import ong.bonanza.beneficiarioapi.domain.entity.Padrinho;
import ong.bonanza.beneficiarioapi.domain.service.BeneficiarioService;
import ong.bonanza.beneficiarioapi.domain.service.DemandaItemService;
import ong.bonanza.beneficiarioapi.domain.service.ItemService;
import ong.bonanza.beneficiarioapi.domain.service.PadrinhoService;
import ong.bonanza.beneficiarioapi.domain.service.PessoaService;

@RequiredArgsConstructor
@Component
public class CadastrarDemandaItemUC {

    private final BeneficiarioService beneficiarioService;

    private final PessoaService pessoaService;

    private final PadrinhoService padrinhoService;

    private final ItemService itemService;

    private final DemandaItemService demandaItemService;

    private final CadastrarDemandaItemUCMapper mapper;

    public void executar(NovaDemandaItemDTO novaDemandaItem) {

        final Beneficiario beneficiario = beneficiarioService.buscarPorId(novaDemandaItem.beneficiarioId);

        demandaItemService.cadastrar(mapper.toDemandaItem(
                padrinhoService.buscarPorPessoaEBeneficiario(
                        pessoaService.buscarPorId(novaDemandaItem.pessoaPadrinhoId), beneficiario),
                beneficiario,
                itemService.buscarPorId(novaDemandaItem.itemId),
                novaDemandaItem));

    }

    public record NovaDemandaItemDTO(
            UUID pessoaPadrinhoId,
            UUID beneficiarioId,
            UUID itemId,
            InformacoesDemandaItemDTO informacoes) {
    }

    public record InformacoesDemandaItemDTO(Integer quantidadeSolicitada, String detalhes) {
    }

    @Mapper
    public interface CadastrarDemandaItemUCMapper {

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "versionNum", ignore = true)
        @Mapping(target = "quantidadeAtendida", ignore = true)
        @Mapping(target = "status", ignore = true)
        @Mapping(target = "quantidadeSolicitada", source = "novaDemandaItemDTO.informacoes.quantidadeSolicitada")
        @Mapping(target = "detalhes", source = "novaDemandaItemDTO.informacoes.detalhes")
        DemandaItem toDemandaItem(
                Padrinho padrinho,
                Beneficiario beneficiario,
                Item item,
                NovaDemandaItemDTO novaDemandaItemDTO);

    }

}
