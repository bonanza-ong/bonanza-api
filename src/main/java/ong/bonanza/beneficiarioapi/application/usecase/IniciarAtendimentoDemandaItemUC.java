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

    public void executar(UUID pessoaProvedoraId, NovoAtendimentoDemandaItemDTO novoAtendimentoDemandaItem) {
        atedimentoDemandaService.inciarAtendimento(mapper.toDoacao(
                novoAtendimentoDemandaItem,
                demandaItemService.buscarPorId(novoAtendimentoDemandaItem.demandaItemId),
                provedorService.buscarPorPessoa(pessoaService.buscarPorId(pessoaProvedoraId))));
    }

    public record NovoAtendimentoDemandaItemDTO(UUID demandaItemId, Integer quantidadeAtendimento) {
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
