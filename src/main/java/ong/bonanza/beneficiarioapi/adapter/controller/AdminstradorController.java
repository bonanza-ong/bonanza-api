package ong.bonanza.beneficiarioapi.adapter.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.application.usecase.CadastrarItemUC;

@RequiredArgsConstructor
@RestController
@RequestMapping("administrador")
public class AdminstradorController {

    private final CadastrarItemUC cadastrarItemUC;

    @PostMapping("/itens")
    public ResponseEntity<CadastrarItemUC.ItemDTO> cadastrarItem(@RequestBody CadastrarItemUC.NovoItemDTO item) {

        CadastrarItemUC.ItemDTO itemCriado = cadastrarItemUC.executar(item);

        return ResponseEntity
                .created(URI.create(String.format("/itens/%s", itemCriado.id())))
                .body(itemCriado);
    }

}
