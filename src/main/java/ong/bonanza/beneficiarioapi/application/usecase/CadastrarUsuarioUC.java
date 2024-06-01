package ong.bonanza.beneficiarioapi.application.usecase;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.application.exception.ForbiddenException;
import ong.bonanza.beneficiarioapi.application.service.AuthService;
import ong.bonanza.beneficiarioapi.domain.entity.Usuario;
import ong.bonanza.beneficiarioapi.domain.service.UsuarioService;

@RequiredArgsConstructor
@Component
public class CadastrarUsuarioUC {

    private final AuthService authService;

    private final CadastrarUsuarioUCMapper mapper;

    private final UsuarioService usuarioService;

    public UUID executar(UUID usuarioId) {

        if (authService.possuiAlgumaRole("administrador")
                || usuarioId.equals(authService.idUsuarioAutenticado()))
            throw new ForbiddenException("cadastrar outro usuário");

        return usuarioService.cadastrar(mapper.toUsuario(usuarioId)).getId();
    }

    @Mapper
    public interface CadastrarUsuarioUCMapper {

        @Mapping(target = "ativo", ignore = true)
        @Mapping(target = "email", ignore = true)
        @Mapping(target = "emailVerificado", ignore = true)
        Usuario toUsuario(UUID id);

    }

}
