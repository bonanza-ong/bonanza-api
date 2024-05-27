package ong.bonanza.beneficiarioapi.adapter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ong.bonanza.beneficiarioapi.application.usecase.BuscarBeneficiarioPaginadoUC;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("beneficiarios")
public class BeneficiarioController {

    private final BuscarBeneficiarioPaginadoUC buscarBeneficiarioPaginadoUC;

    @Operation(summary = "Buscar beneficiários", description = "Busca beneficiários com paginação e por ordem de último atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BuscarBeneficiarioPaginadoUC.BeneficiarioDTO.class)))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    ResponseEntity<List<BuscarBeneficiarioPaginadoUC.BeneficiarioDTO>> BuscarBeneficiarios(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        return ResponseEntity.ok(buscarBeneficiarioPaginadoUC.executar(page, size));
    }
}
