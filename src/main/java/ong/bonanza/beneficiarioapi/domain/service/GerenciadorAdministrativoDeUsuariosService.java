package ong.bonanza.beneficiarioapi.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.client.AdminAuthClient;
import ong.bonanza.beneficiarioapi.domain.entity.Usuario;
import ong.bonanza.beneficiarioapi.domain.exception.UsuarioNaoEncontradoException;

@RequiredArgsConstructor
@Service
public class GerenciadorAdministrativoDeUsuariosService {

    private final AdminAuthClient authClientService;

    public Usuario buscarPorId(UUID id) {
        return authClientService
                .buscarUsuarioPorId(id)
                .orElseThrow(() -> UsuarioNaoEncontradoException.buscaPorId(id));
    }

    public List<Usuario> buscarPorEmail(String email, Integer max) {
        return authClientService.buscarUsuariosPorEmail(email, max);
    }

}
