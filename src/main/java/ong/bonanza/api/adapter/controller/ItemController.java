package ong.bonanza.api.adapter.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import ong.bonanza.api.application.usecase.BuscarItensPaginadoUC;
import ong.bonanza.api.application.usecase.CadastrarItemUC;

@RequiredArgsConstructor
@RestController
@RequestMapping("itens")
public class ItemController {

    private final CadastrarItemUC cadastrarItemUC;

    private final BuscarItensPaginadoUC buscarItensPaginadoUC;

    @Operation(summary = "Cadastra Item", security = @SecurityRequirement(name = "bearerAuth"), description = "Cadastra Item em uma categoria, caso a categoria não exista será retornado um erro 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CadastrarItemUC.ItemDTO.class)))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<CadastrarItemUC.ItemDTO> cadastrarItem(@RequestBody CadastrarItemUC.NovoItemDTO item) {

        CadastrarItemUC.ItemDTO itemCriado = cadastrarItemUC.executar(item);

        return ResponseEntity
                .created(URI.create(String.format("/itens/%s", itemCriado.id())))
                .body(itemCriado);
    }

    @Operation(summary = "Busca por todos os itens, podendo filtrar por nome", security = @SecurityRequirement(name = "bearerAuth"), description = "Busca por todos os itens utilizando de paginação e ordem de último atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarItensPaginadoUC.ItemDTO.class)))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<List<BuscarItensPaginadoUC.ItemDTO>> buscarItemPaginado(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "nome", defaultValue = "") String nome) {
        return ResponseEntity.ok(buscarItensPaginadoUC.executar(page, size, nome));
    }
}
