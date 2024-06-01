package ong.bonanza.beneficiarioapi.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.application.exception.ForbiddenException;
import ong.bonanza.beneficiarioapi.application.service.AuthService;
import ong.bonanza.beneficiarioapi.domain.entity.Padrinho;
import ong.bonanza.beneficiarioapi.domain.entity.Pessoa;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusPadrinho;
import ong.bonanza.beneficiarioapi.domain.service.PadrinhoService;
import ong.bonanza.beneficiarioapi.domain.service.PessoaService;
import ong.bonanza.beneficiarioapi.domain.service.UsuarioService;

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

        return padrinhoService
                .cadastrar(mapper.toPadrinho(pessoaService.buscarPorUsuario(
                        usuarioService.buscarPorId(usuarioId)),
                        status))
                .getId();
    }

    @Mapper
    public interface CadastrarPadrinhoUCMapper {

        @Mapping(target = "pessoa", source = "pessoa")
        @Mapping(target = "apadrinhados", ignore = true)
        Padrinho toPadrinho(Pessoa pessoa, StatusPadrinho status);

    }

}
