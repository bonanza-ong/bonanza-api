package ong.bonanza.beneficiarioapi.adapter.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.application.usecase.CadastrarItemUC;

@RequiredArgsConstructor
@RestController
@RequestMapping("administrador")
public class AdminstradorController {

    private final CadastrarItemUC cadastrarItemUC;

    @Operation(summary = "Cadastra Item", description = "Cadastra Item em uma categoria, caso a categoria não exista será retornado um erro 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CadastrarItemUC.ItemDTO.class)))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/itens")
    public ResponseEntity<CadastrarItemUC.ItemDTO> cadastrarItem(@RequestBody CadastrarItemUC.NovoItemDTO item) {

        CadastrarItemUC.ItemDTO itemCriado = cadastrarItemUC.executar(item);

        return ResponseEntity
                .created(URI.create(String.format("/itens/%s", itemCriado.id())))
                .body(itemCriado);
    }

}
