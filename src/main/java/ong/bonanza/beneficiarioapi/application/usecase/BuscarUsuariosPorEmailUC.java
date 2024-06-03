package ong.bonanza.beneficiarioapi.application.usecase;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.entity.Usuario;
import ong.bonanza.beneficiarioapi.domain.service.GerenciadorAdministrativoDeUsuariosService;

@RequiredArgsConstructor
@Component
public class BuscarUsuariosPorEmailUC {

    private final BuscarUsuariosPorEmailUCMapper mapper;

    private final GerenciadorAdministrativoDeUsuariosService gerenciadorAdministrativoDeUsuariosService;

    public List<UsuarioDTO> executar(ParametrosBuscaDTO parametros) {
        return mapper.toUsuarioDTOList(
                gerenciadorAdministrativoDeUsuariosService.buscarPorEmail(parametros.email, parametros.max));
    }

    public record ParametrosBuscaDTO(String email, Integer max) {
    }

    public record UsuarioDTO(
            UUID id,
            String email,
            boolean emailVerificado,
            boolean ativo) {
    }

    @Mapper
    public interface BuscarUsuariosPorEmailUCMapper {

        List<UsuarioDTO> toUsuarioDTOList(List<Usuario> usuarioList);

    }

}
