package ong.bonanza.beneficiarioapi.adapter.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import ong.bonanza.beneficiarioapi.adapter.provider.AuthenticationProvider;
import ong.bonanza.beneficiarioapi.application.usecase.BuscarBeneficiarioPaginadoUC;
import ong.bonanza.beneficiarioapi.application.usecase.CadastrarDemandaItemUC;
import ong.bonanza.beneficiarioapi.application.usecase.IniciarAtendimentoDemandaItemUC;

@RestController
@RequiredArgsConstructor
@RequestMapping("beneficiarios")
public class BeneficiarioController {

    private final AuthenticationProvider authenticationProvider;

    private final BuscarBeneficiarioPaginadoUC buscarBeneficiarioPaginadoUC;

    private final IniciarAtendimentoDemandaItemUC iniciarAtendimentoDemandaItemUC;

    private final CadastrarDemandaItemUC cadastrarDemandaItemUC;

    @Operation(summary = "Buscar beneficiários", security = @SecurityRequirement(name = "bearerAuth"), description = "Busca beneficiários com paginação e por ordem de último atualizado")
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

    @Operation(summary = "Inicia atendimento demanda item", security = @SecurityRequirement(name = "bearerAuth"), description = "Inicia Atendimento de demanda, se mais de um provedor tentar pegar a mesma demanda ao mesmo tempo será lancaçado um erro de conflito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = IniciarAtendimentoDemandaItemUC.AtendimentoDemandaItemDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "422", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("{beneficiarioId}/demandas-itens/{demandaItemId}/atendimentos")
    ResponseEntity<IniciarAtendimentoDemandaItemUC.AtendimentoDemandaItemDTO> iniciarAtendimentoDemanda(
            @PathVariable UUID beneficiarioId,
            @PathVariable UUID demandaItemId,
            @RequestParam(value = "usuarioAtendimentoId", required = false) UUID usuarioAtendimentoId,
            @RequestBody Integer quantidadeAtendimento) {

        IniciarAtendimentoDemandaItemUC.AtendimentoDemandaItemDTO atendimento = iniciarAtendimentoDemandaItemUC
                .executar(new IniciarAtendimentoDemandaItemUC.NovoAtendimentoDemandaItem(
                        usuarioAtendimentoId == null ? authenticationProvider.authenticatedUserId()
                                : usuarioAtendimentoId,
                        beneficiarioId,
                        demandaItemId,
                        quantidadeAtendimento));

        return ResponseEntity
                .created(URI.create(String.format("beneficiarios/%s/demandas-itens/%s/atendimentos/%s",
                        beneficiarioId.toString(),
                        demandaItemId.toString(),
                        atendimento)))
                .body(atendimento);

    }

    @Operation(summary = "Cadastrar nova demanda item", security = @SecurityRequirement(name = "bearerAuth"), description = "Cadastra uma nova demanda por item, caso beneficiario não seja apadrinhado pelo mesmo padrinho que solicitou o cadastro, será lançado um 404 (Não existe beneficário com este padrinho)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = CadastrarDemandaItemUC.DemandaItemDTO.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("{beneficiarioId}/demandas-itens")
    ResponseEntity<CadastrarDemandaItemUC.DemandaItemDTO> iniciarAtendimentoDemanda(
            @PathVariable UUID beneficiarioId,
            @RequestParam(value = "usuarioSolicitanteId", required = false) UUID usuarioSolicitanteId,
            @RequestBody CadastrarDemandaItemUC.InformacoesItemDemandaItemDTO informacoes) {

        UUID solicitanteId = usuarioSolicitanteId == null ? authenticationProvider.authenticatedUserId()
                : usuarioSolicitanteId;

        final CadastrarDemandaItemUC.DemandaItemDTO demandaItem = cadastrarDemandaItemUC
                .executar(new CadastrarDemandaItemUC.NovaDemandaItemDTO(
                        solicitanteId,
                        beneficiarioId,
                        informacoes));

        return ResponseEntity
                .created(URI.create(String.format("beneficiarios/%s/demandas-itens/%s",
                        beneficiarioId.toString(),
                        demandaItem.id().toString())))
                .body(demandaItem);

    }

}
