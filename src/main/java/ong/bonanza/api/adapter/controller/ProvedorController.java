package ong.bonanza.api.adapter.controller;

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
import ong.bonanza.api.application.usecase.BuscarProvedoresPaginadoUC;

@RequiredArgsConstructor
@RestController
@RequestMapping("provedores")
public class ProvedorController {

        private final BuscarProvedoresPaginadoUC buscarProvedoresPaginadoUC;

        @Operation(summary = "Busca por todos os provedores, podendo filtrar por nome", security = @SecurityRequirement(name = "bearerAuth"), description = "Busca por todos os provedores utilizando de paginação")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarProvedoresPaginadoUC.ProvedorDTO.class)))),
                        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
        })
        @GetMapping
        public ResponseEntity<List<BuscarProvedoresPaginadoUC.ProvedorDTO>> buscarPaginado(
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "10") int size,
                        @RequestParam(value = "nome", defaultValue = "") String nome) {

                return ResponseEntity.ok(buscarProvedoresPaginadoUC
                                .executar(new BuscarProvedoresPaginadoUC.BuscaProvedoresPaginadaDTO(nome, page, size)));

        }

}
