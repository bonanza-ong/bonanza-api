package ong.bonanza.api.application.usecase;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.Beneficiario;
import ong.bonanza.api.domain.entity.Pessoa;
import ong.bonanza.api.domain.service.BeneficiarioService;

@RequiredArgsConstructor
@Component
public class BuscarBeneficiariosPaginadoUC {

    private final BuscarBeneficiariosPaginadoUCMapper mapper;

    private final BeneficiarioService beneficiarioService;

    public List<BeneficiarioDTO> executar(int page, int size) {
        return mapper.toBeneficiarioList(beneficiarioService.buscarTodosPaginado(page, size));
    }

    public record BeneficiarioDTO(UUID id, String sobre, PessoaDTO pessoa) {
    }
    
    public record PessoaDTO(UUID id, String nome) {}

    @Mapper
    public interface BuscarBeneficiariosPaginadoUCMapper {
        List<BeneficiarioDTO> toBeneficiarioList(List<Beneficiario> beneficiarios);
        PessoaDTO toPessoaDTO(Pessoa pessoa);
    }

}
