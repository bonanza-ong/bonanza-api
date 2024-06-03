package ong.bonanza.api.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.api.application.exception.ForbiddenException;
import ong.bonanza.api.application.service.AuthService;
import ong.bonanza.api.domain.entity.Usuario;
import ong.bonanza.api.domain.service.GerenciadorAdministrativoDeUsuariosService;
import ong.bonanza.api.domain.service.UsuarioService;

@RequiredArgsConstructor
@Component
public class CadastrarUsuarioUC {

    private final AuthService authService;

    private final GerenciadorAdministrativoDeUsuariosService gerenciadorAdministrativoDeUsuariosService;

    private final CadastrarUsuarioUCMapper mapper;

    private final UsuarioService usuarioService;

    public UUID executar(UUID usuarioId) {

        if (authService.possuiAlgumaRole("administrador"))
            return cadastrar(gerenciadorAdministrativoDeUsuariosService.buscarPorId(usuarioId));

        if (!usuarioId.equals(authService.idUsuarioAutenticado()))
            throw new ForbiddenException("cadastrar outro usuário");

        return cadastrar(mapper.toUsuario(usuarioId));
    }

    private UUID cadastrar(Usuario usuario) {
        return usuarioService.cadastrar(usuario).getId();
    }

    @Mapper
    public interface CadastrarUsuarioUCMapper {

        @Mapping(target = "ativo", ignore = true)
        @Mapping(target = "email", ignore = true)
        @Mapping(target = "emailVerificado", ignore = true)
        Usuario toUsuario(UUID id);

    }

}
