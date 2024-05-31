package ong.bonanza.beneficiarioapi.adapter.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.adapter.provider.AuthenticationProvider;
import ong.bonanza.beneficiarioapi.application.usecase.CadastrarPessoaUC;

@RequiredArgsConstructor
@RestController
@RequestMapping("pessoas")
public class PessoaController {

    private final AuthenticationProvider authenticationProvider;

    private final CadastrarPessoaUC cadastrarPessoaUC;

    @Operation(summary = "Cadastrar-se como pessoa na Base de Dados", security = @SecurityRequirement(name = "bearerAuth"), description = "Se cadastra na base através de seu token de acesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = CadastrarPessoaUC.PessoaDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "422", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<CadastrarPessoaUC.PessoaDTO> cadastrarse(
            @RequestBody String nome) {

        final CadastrarPessoaUC.PessoaDTO pessoa = cadastrarPessoaUC
                .executar(new CadastrarPessoaUC.NovaPessoaDTO(authenticationProvider.authenticatedUserId(), nome));

        return ResponseEntity
                .created(URI.create(String.format("pessoas/%s", pessoa.id().toString())))
                .body(pessoa);

    }

}
