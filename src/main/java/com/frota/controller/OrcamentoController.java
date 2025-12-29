package com.frota.controller;

import com.frota.dto.OrcamentoRequestDTO;
import com.frota.model.Orcamento;
import com.frota.service.FileStorageService;
import com.frota.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/orcamentos")
@CrossOrigin(origins = "*")
public class OrcamentoController {

    @Autowired
    private OrcamentoService service;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private OrcamentoService orcamentoService;


    @PostMapping(value = "/enviar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Orcamento> enviarOrcamento(
            @RequestParam("manutencaoId") Long manutencaoId,
            @RequestParam("valorTotal") BigDecimal valorTotal,
            @RequestParam("oficinaId") Long oficinaId,
            @RequestParam("arquivo") MultipartFile arquivo) {

        // Aqui você chamaria um serviço para:
        // 1. Salvar o arquivo na pasta ./uploads
        // 2. Salvar o registro no banco com o caminho do arquivo

        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Orcamento> criar(
            @RequestPart("dados") OrcamentoRequestDTO dto,
            @RequestPart("arquivo") MultipartFile arquivo) {

        // 1. Salva o arquivo físico
        String nomeArquivo = fileStorageService.salvar(arquivo);

        // 2. Chama o service para salvar no banco (vamos criar abaixo)
        return ResponseEntity.ok(orcamentoService.salvar(dto, nomeArquivo));
    }
    @PutMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovar(@PathVariable Long id) {
        orcamentoService.aprovar(id);
        return ResponseEntity.ok().build();
    }

}