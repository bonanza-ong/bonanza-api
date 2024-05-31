package ong.bonanza.beneficiarioapi.adapter.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.application.usecase.AutoCadastrarUsuarioUC;
import ong.bonanza.beneficiarioapi.application.usecase.BuscarUsuariosPorEmailUC;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final BuscarUsuariosPorEmailUC buscarUsuariosPorEmailUC;

    private final AutoCadastrarUsuarioUC autoCadastrarUsuarioUC;

    @Operation(summary = "Buscar usuários", security = @SecurityRequirement(name = "bearerAuth"), description = "Busca usuários cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarUsuariosPorEmailUC.UsuarioDTO.class)))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<List<BuscarUsuariosPorEmailUC.UsuarioDTO>> buscarPorEmail(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "max", defaultValue = "10") int max) {

        return ResponseEntity
                .ok(buscarUsuariosPorEmailUC.executar(new BuscarUsuariosPorEmailUC.ParametrosBuscaDTO(email, max)));

    }

    @Operation(summary = "Cadastrar-se como usuário na Base de Dados", security = @SecurityRequirement(name = "bearerAuth"), description = "Se cadastra na base através de seu token de acesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "422", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Void> cadastrarse(
            @RequestBody String nome) {

        final UUID usuarioId = autoCadastrarUsuarioUC.executar();

        return ResponseEntity
                .created(URI.create(String.format("usuarios/informacoes", usuarioId.toString())))
                .build();

    }

}
