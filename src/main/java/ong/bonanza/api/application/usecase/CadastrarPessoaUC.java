package ong.bonanza.api.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.application.exception.ForbiddenException;
import ong.bonanza.api.application.service.AuthService;
import ong.bonanza.api.domain.entity.Pessoa;
import ong.bonanza.api.domain.entity.Usuario;
import ong.bonanza.api.domain.service.PessoaService;
import ong.bonanza.api.domain.service.UsuarioService;

@RequiredArgsConstructor
@Component
public class CadastrarPessoaUC {

    private final AuthService authService;

    private final CadastrarPessoaUCMapper mapper;

    private final UsuarioService usuarioService;

    private final PessoaService pessoaService;

    public PessoaDTO executar(NovaPessoaDTO novaPessoa) {

        if (!(authService.possuiAlgumaRole("administrador")
                || novaPessoa.usuarioId.equals(authService.idUsuarioAutenticado())))
            throw new ForbiddenException("cadastrar outra pessoa");

        return cadastrar(novaPessoa, usuarioService.buscarPorId(novaPessoa.usuarioId));
    }

    private PessoaDTO cadastrar(NovaPessoaDTO novaPessoa, Usuario usuario) {
        return mapper.toPessoaDTO(pessoaService.cadastrar(mapper.toPessoa(usuario, novaPessoa.nome)));
    }

    public record NovaPessoaDTO(UUID usuarioId, String nome) {
    }

    public record PessoaDTO(UUID id, UUID usuarioId, String nome) {
    }

    @Mapper
    public interface CadastrarPessoaUCMapper {

        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "usuario", source = "usuario")
        Pessoa toPessoa(Usuario usuario, String nome);

        @Mapping(target = "usuarioId", source = "usuario.id")
        PessoaDTO toPessoaDTO(Pessoa pessoa);

    }

}
