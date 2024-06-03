package ong.bonanza.api.application.usecase;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.Provedor;
import ong.bonanza.api.domain.service.ProvedorService;

@RequiredArgsConstructor
@Component
public class BuscarProvedoresPaginadoUC {

    private final BuscarProvedoresPaginadoUCMapper mapper;

    private final ProvedorService provedorService;

    public List<ProvedorDTO> executar(BuscaProvedoresPaginadaDTO busca) {
        return mapper.toProvedorDTOList(provedorService.buscarPaginado(busca.nome(), busca.page, busca.size));
    }

    public record BuscaProvedoresPaginadaDTO(String nome, int page, int size) {
    }

    public record ProvedorDTO(
            UUID id,
            UUID pessoaId,
            UUID usuarioId,
            String nome,
            String sobre) {
    }

    @Mapper
    public interface BuscarProvedoresPaginadoUCMapper {

        List<ProvedorDTO> toProvedorDTOList(List<Provedor> provedores);

        @Mapping(target = "pessoaId", source = "pessoa.id")
        @Mapping(target = "usuarioId", source = "pessoa.usuario.id")
        @Mapping(target = "nome", source = "pessoa.nome")
        ProvedorDTO toProvedorDTO(Provedor provedor);

    }

}
