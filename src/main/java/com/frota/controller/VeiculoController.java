package com.frota.controller;

import com.frota.dto.VeiculoDTO;
import com.frota.model.Veiculo;
import com.frota.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/api/veiculos")
    @CrossOrigin(origins = "http://localhost:5173") // Libera para o React (Vite)
    public class VeiculoController {

        @Autowired
        private VeiculoRepository repository;

        @GetMapping
        public List<VeiculoDTO> listarTodos() {
            return repository.findAll().stream()
                    .map(v -> new VeiculoDTO(v.getId(), v.getPlaca(), v.getModelo(), v.getKmAtual()))
                    .toList();
        }
        @PostMapping
        public ResponseEntity<VeiculoDTO> criar(@RequestBody Veiculo veiculo) {
            // Salva o veículo vindo do React
            Veiculo salvo = repository.save(veiculo);

            // Retorna o DTO para o frontend
            VeiculoDTO dto = new VeiculoDTO(salvo.getId(), salvo.getPlaca(), salvo.getModelo(), salvo.getKmAtual());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }
    }
