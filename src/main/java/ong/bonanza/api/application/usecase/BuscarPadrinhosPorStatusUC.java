package ong.bonanza.api.application.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.domain.entity.EnderecoPessoa;
import ong.bonanza.api.domain.entity.Padrinho;
import ong.bonanza.api.domain.enumeration.StatusPadrinho;
import ong.bonanza.api.domain.service.EnderecoPessoaService;
import ong.bonanza.api.domain.service.PadrinhoService;

@RequiredArgsConstructor
@Component
public class BuscarPadrinhosPorStatusUC {

    private final BuscarPadrinhosPorStatusUCMapper mapper;

    private final PadrinhoService padrinhoService;

    private final EnderecoPessoaService enderecoPessoaService;

    public List<PadrinhoDTO> executar(BuscaPadrinhoStatusDTO busca) {
        return padrinhoService
                .buscarPorStatus(busca.status, busca.page, busca.size)
                .stream()
                .map(this::converter)
                .toList();
    }

    public record BuscaPadrinhoStatusDTO(
            StatusPadrinho status,
            int page,
            int size) {
    }

    private PadrinhoDTO converter(Padrinho padrinho) {
        return mapper.toPadrinhoDTO(padrinho, enderecoPessoaService.buscarPorPessoa(padrinho.getPessoa()));
    }

    public record PadrinhoDTO(
            UUID id,
            UUID pessoaId,
            UUID usuarioId,
            String nome,
            StatusPadrinho status,
            List<EnderecoDTO> enderecos,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
    }

    public record EnderecoDTO(
            UUID id,
            String cep,
            String logradouro,
            Integer numero,
            String complemento,
            String cidade,
            String bairro,
            LocalizacaoDTO localizacao) {
    }

    public record LocalizacaoDTO(
            UUID id,
            String geocode,
            String geohash,
            BigDecimal latitude,
            BigDecimal longitude) {
    }

    @Mapper
    public interface BuscarPadrinhosPorStatusUCMapper {

        @Mapping(target = "id", source = "padrinho.id")
        @Mapping(target = "pessoaId", source = "padrinho.pessoa.id")
        @Mapping(target = "usuarioId", source = "padrinho.pessoa.usuario.id")
        @Mapping(target = "nome", source = "padrinho.pessoa.nome")
        PadrinhoDTO toPadrinhoDTO(Padrinho padrinho, List<EnderecoPessoa> enderecos);

    }

}
