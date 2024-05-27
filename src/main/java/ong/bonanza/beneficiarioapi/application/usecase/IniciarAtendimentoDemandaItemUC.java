package ong.bonanza.beneficiarioapi.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;
import ong.bonanza.beneficiarioapi.domain.entity.Doacao;
import ong.bonanza.beneficiarioapi.domain.entity.Provedor;
import ong.bonanza.beneficiarioapi.domain.service.AtedimentoDemandaService;
import ong.bonanza.beneficiarioapi.domain.service.BeneficiarioService;
import ong.bonanza.beneficiarioapi.domain.service.DemandaItemService;
import ong.bonanza.beneficiarioapi.domain.service.PessoaService;
import ong.bonanza.beneficiarioapi.domain.service.ProvedorService;

@RequiredArgsConstructor
@Component
public class IniciarAtendimentoDemandaItemUC {

    private final InciarAtendimentoDemandaItemUCMapper mapper;

    private final AtedimentoDemandaService atedimentoDemandaService;

    private final PessoaService pessoaService;

    private final ProvedorService provedorService;

    private final DemandaItemService demandaItemService;

    private final BeneficiarioService beneficiarioService;

    public UUID executar(UUID pessoaProvedoraId, NovoAtendimentoDemandaItemDTO novoAtendimentoDemandaItem) {
        return atedimentoDemandaService.inciarAtendimento(mapper.toDoacao(
                novoAtendimentoDemandaItem,
                demandaItemService.buscarPorIdEBeneficiario(
                        novoAtendimentoDemandaItem.demandaItemId,
                        beneficiarioService.buscarPorId(novoAtendimentoDemandaItem.beneficiarioId)),
                provedorService
                        .buscarPorPessoa(pessoaService.buscarPorId(pessoaProvedoraId))))
                .getId();
    }

    public record NovoAtendimentoDemandaItemDTO(
            UUID beneficiarioId,
            UUID demandaItemId,
            Integer quantidadeAtendimento) {
    }

    @Mapper
    public interface InciarAtendimentoDemandaItemUCMapper {

        @Mapping(target = "demanda", source = "demandaItem")
        @Mapping(target = "quantidade", source = "novoAtendimentoDemandaItemDTO.quantidadeAtendimento")
        @Mapping(target = "status", ignore = true)
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        Doacao toDoacao(
                NovoAtendimentoDemandaItemDTO novoAtendimentoDemandaItemDTO,
                DemandaItem demandaItem,
                Provedor provedor);

    }

}
