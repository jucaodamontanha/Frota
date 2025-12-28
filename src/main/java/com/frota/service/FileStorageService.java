package com.frota.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final Path root = Paths.get("./uploads/orcamentos");

    public String salvar(MultipartFile arquivo) {
        try {
            // Cria a pasta se não existir
            if (!Files.exists(root)) Files.createDirectories(root);

            // Gera um nome único para o arquivo (evita sobrescrever)
            String nomeArquivo = System.currentTimeMillis() + "_" + arquivo.getOriginalFilename();
            Files.copy(arquivo.getInputStream(), this.root.resolve(nomeArquivo));

            return nomeArquivo; // Retornamos o nome para salvar no banco
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}