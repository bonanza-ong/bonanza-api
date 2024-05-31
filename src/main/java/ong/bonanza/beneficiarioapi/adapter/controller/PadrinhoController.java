package ong.bonanza.beneficiarioapi.adapter.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import ong.bonanza.beneficiarioapi.adapter.provider.AuthenticationProvider;
import ong.bonanza.beneficiarioapi.application.usecase.BuscarPadrinhosPorStatusUC;
import ong.bonanza.beneficiarioapi.application.usecase.CadastrarPadrinhoUC;
import ong.bonanza.beneficiarioapi.application.usecase.CadastrarPessoaUC;
import ong.bonanza.beneficiarioapi.domain.enumeration.StatusPadrinho;

@RequiredArgsConstructor
@RestController
@RequestMapping("padrinhos")
public class PadrinhoController {

    private final AuthenticationProvider authenticationProvider;

    private final CadastrarPadrinhoUC cadastrarPadrinhoUC;

    private final BuscarPadrinhosPorStatusUC buscarPadrinhosPorStatusUC;

    @Operation(summary = "Cadastrar-se como padrinho na Base de Dados", security = @SecurityRequirement(name = "bearerAuth"), description = "Se cadastra na base através de seu token de acesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = CadastrarPessoaUC.PessoaDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "422", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<UUID> cadastrarse() {

        final UUID padrinhoId = cadastrarPadrinhoUC
                .executar(authenticationProvider.authenticatedUserId(), StatusPadrinho.AGUARDANDO_VERIFICACAO);

        return ResponseEntity
                .created(URI.create(String.format("padrinhos/%s", padrinhoId.toString())))
                .body(padrinhoId);

    }

    @Operation(summary = "Busca padrinhos na Base de Dados dado um Status", security = @SecurityRequirement(name = "bearerAuth"), description = "Status default é \"VERIFICADO\"")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarPadrinhosPorStatusUC.PadrinhoDTO.class)))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<List<BuscarPadrinhosPorStatusUC.PadrinhoDTO>> buscarPadrinhos(
            @RequestParam(value = "status", defaultValue = "VERIFICADO", required = false) StatusPadrinho status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(buscarPadrinhosPorStatusUC
                .executar(new BuscarPadrinhosPorStatusUC.BuscaPadrinhoStatusDTO(status, page, size)));
    }

}
