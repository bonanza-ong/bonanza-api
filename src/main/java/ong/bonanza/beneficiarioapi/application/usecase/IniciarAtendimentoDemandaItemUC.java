package ong.bonanza.beneficiarioapi.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.AtendimentoDemandaItem;
import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;
import ong.bonanza.beneficiarioapi.domain.entity.Provedor;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusAtendimentoDemandaItem;
import ong.bonanza.beneficiarioapi.domain.service.AtendimentoDemandaItemService;
import ong.bonanza.beneficiarioapi.domain.service.BeneficiarioService;
import ong.bonanza.beneficiarioapi.domain.service.DemandaItemService;
import ong.bonanza.beneficiarioapi.domain.service.PessoaService;
import ong.bonanza.beneficiarioapi.domain.service.ProvedorService;

@RequiredArgsConstructor
@Component
public class IniciarAtendimentoDemandaItemUC {

    private final InciarAtendimentoDemandaItemUCMapper mapper;

    private final AtendimentoDemandaItemService atendimentoDemandaItemService;

    private final PessoaService pessoaService;

    private final ProvedorService provedorService;

    private final DemandaItemService demandaItemService;

    private final BeneficiarioService beneficiarioService;

    public AtendimentoDemandaItemDTO executar(NovoAtendimentoDemandaItem novoAtendimento) {
        return mapper.toAtendimentoDemandaItemDTO(
                novoAtendimento.quantidadeAtendimento,
                atendimentoDemandaItemService.inciar(mapper.toAtendimentoDemandaItem(
                        novoAtendimento.quantidadeAtendimento,
                        demandaItemService.buscarPorIdEBeneficiario(
                                novoAtendimento.demandaItemId,
                                beneficiarioService.buscarPorId(novoAtendimento.beneficiarioId)),
                        provedorService
                                .buscarPorPessoa(pessoaService.buscarPorId(novoAtendimento.pessoaProvedoraId)))));
    }

    public record NovoAtendimentoDemandaItem(
            UUID pessoaProvedoraId,
            UUID beneficiarioId,
            UUID demandaItemId,
            Integer quantidadeAtendimento) {
    }

    public record AtendimentoDemandaItemDTO(
            UUID id,
            StatusAtendimentoDemandaItem status,
            Integer quantidadeProvida,
            DemandaItemDTO demanda) {
    }

    public record DemandaItemDTO(
            UUID id,
            UUID beneficiarioId,
            Integer quantidadeSolicitada,
            Integer quantidadeAtendida,
            ItemDTO item) {
    }

    public record ItemDTO(
            UUID id,
            String nome) {
    }

    @Mapper
    public interface InciarAtendimentoDemandaItemUCMapper {

        @Mapping(target = "demanda", source = "demandaItem")
        @Mapping(target = "quantidade", source = "quantidadeAtendimento")
        @Mapping(target = "status", ignore = true)
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        AtendimentoDemandaItem toAtendimentoDemandaItem(
                Integer quantidadeAtendimento,
                DemandaItem demandaItem,
                Provedor provedor);

        @Mapping(target = "demanda.beneficiarioId", source = "atendimentoDemanda.demanda.beneficiario.id")
        AtendimentoDemandaItemDTO toAtendimentoDemandaItemDTO(
                Integer quantidadeProvida,
                AtendimentoDemandaItem atendimentoDemanda);

    }

}
