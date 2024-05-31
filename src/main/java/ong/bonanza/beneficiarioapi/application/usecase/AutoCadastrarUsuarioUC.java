package ong.bonanza.beneficiarioapi.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.service.UsuarioService;

@RequiredArgsConstructor
@Component
public class AutoCadastrarUsuarioUC {

    private final UsuarioService usuarioService;

    public UUID executar() {
        return usuarioService.cadastrar(usuarioService.atualAutenticado()).getId();
    }

}
