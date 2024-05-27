package ong.bonanza.beneficiarioapi.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.DemandaItem;
import ong.bonanza.beneficiarioapi.domain.entity.Doacao;
import ong.bonanza.beneficiarioapi.domain.entity.Provedor;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusDoacao;
import ong.bonanza.beneficiarioapi.domain.service.DemandaItemService;
import ong.bonanza.beneficiarioapi.domain.service.DoacaoService;
import ong.bonanza.beneficiarioapi.domain.service.PessoaService;
import ong.bonanza.beneficiarioapi.domain.service.ProvedorService;

@RequiredArgsConstructor
@Component
public class PegarDemandaItemUC {

    private final PegarDemandaItemUCMapper mapper;

    private final PessoaService pessoaService;

    private final ProvedorService provedorService;

    private final DemandaItemService demandaItemService;

    private final DoacaoService doacaoService;

    public void executar(UUID pessoaProvedoraId, NovoAtendimentoDemandaItemDTO novoAtendimentoDemandaItem) {
        doacaoService.efetuarDoacao(mapper.toDoacao(
                novoAtendimentoDemandaItem,
                StatusDoacao.ATRIBUIDA,
                demandaItemService.buscarPorId(novoAtendimentoDemandaItem.demandaItemId),
                provedorService.buscarPorPessoa(pessoaService.buscarPorId(pessoaProvedoraId))));
    }

    public record NovoAtendimentoDemandaItemDTO(UUID demandaItemId, Integer quantidadeAtendida) {
    }

    @Mapper
    public interface PegarDemandaItemUCMapper {

        @Mapping(target = "demanda", source = "demandaItem")
        @Mapping(target = "status", source = "status")
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        Doacao toDoacao(
                NovoAtendimentoDemandaItemDTO novoAtendimentoDemandaItemDTO,
                StatusDoacao status,
                DemandaItem demandaItem,
                Provedor provedor);

    }

}
