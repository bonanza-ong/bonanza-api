package ong.bonanza.api.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.application.exception.ForbiddenException;
import ong.bonanza.api.application.service.AuthService;
import ong.bonanza.api.domain.entity.Padrinho;
import ong.bonanza.api.domain.entity.Pessoa;
import ong.bonanza.api.domain.entity.Usuario;
import ong.bonanza.api.domain.enumeration.StatusPadrinho;
import ong.bonanza.api.domain.service.PadrinhoService;
import ong.bonanza.api.domain.service.PessoaService;
import ong.bonanza.api.domain.service.UsuarioService;

@RequiredArgsConstructor
@Component
public class CadastrarPadrinhoUC {

    private final AuthService authService;

    private final CadastrarPadrinhoUCMapper mapper;

    private final UsuarioService usuarioService;

    private final PessoaService pessoaService;

    private final PadrinhoService padrinhoService;

    public UUID executar(UUID usuarioId, StatusPadrinho status) {

        if (!(authService.possuiAlgumaRole("administrador")
                || usuarioId.equals(authService.idUsuarioAutenticado())))
            throw new ForbiddenException("cadastrar outro padrinho");

        return cadastrar(usuarioService.buscarPorId(usuarioId), status);

    }

    private UUID cadastrar(Usuario usuario, StatusPadrinho status) {
        return padrinhoService
                .cadastrar(mapper.toPadrinho(pessoaService.buscarPorUsuario(usuario), status))
                .getId();
    }

    @Mapper
    public interface CadastrarPadrinhoUCMapper {

        @Mapping(target = "pessoa", source = "pessoa")
        @Mapping(target = "apadrinhados", ignore = true)
        Padrinho toPadrinho(Pessoa pessoa, StatusPadrinho status);

    }

}
