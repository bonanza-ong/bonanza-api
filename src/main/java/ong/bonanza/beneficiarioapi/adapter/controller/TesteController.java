package ong.bonanza.beneficiarioapi.adapter.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ong.bonanza.beneficiarioapi.domain.repository.ItemRepository;

@RequiredArgsConstructor
@RestController
public class TesteController {

    private final ItemRepository itemRepository;

    @GetMapping("/aham")
    public ResponseEntity<?> getMethodName(@RequestParam UUID param) {
        return ResponseEntity.ok(itemRepository.findItemsByCategoriaId(param));
    }

}
