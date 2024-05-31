package ong.bonanza.beneficiarioapi.adapter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import ong.bonanza.beneficiarioapi.application.usecase.BuscarUsuariosPorEmailUC;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final BuscarUsuariosPorEmailUC buscarUsuariosPorEmailUC;

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

}
