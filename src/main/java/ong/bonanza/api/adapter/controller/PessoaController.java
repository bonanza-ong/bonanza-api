package ong.bonanza.api.adapter.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import ong.bonanza.api.adapter.provider.AuthenticationProvider;
import ong.bonanza.api.application.usecase.CadastrarPessoaUC;

@RequiredArgsConstructor
@RestController
@RequestMapping("pessoas")
public class PessoaController {

    private final AuthenticationProvider authenticationProvider;

    private final CadastrarPessoaUC cadastrarPessoaUC;

    @Operation(summary = "Cadastrar pessoa na Base de Dados", security = @SecurityRequirement(name = "bearerAuth"), description = "Se cadastra na base através de seu token de acesso, ou cadastra um outra pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = CadastrarPessoaUC.PessoaDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "422", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<CadastrarPessoaUC.PessoaDTO> cadastrar(
            @RequestBody String nome,
            @RequestParam(value = "usuarioId", required = false) UUID usuarioId) {

        final CadastrarPessoaUC.PessoaDTO pessoa = cadastrarPessoaUC
                .executar(new CadastrarPessoaUC.NovaPessoaDTO(
                        usuarioId == null ? authenticationProvider.authenticatedUserId() : usuarioId,
                        nome));

        return ResponseEntity
                .created(URI.create(String.format("pessoas/%s", pessoa.id().toString())))
                .body(pessoa);

    }

}
