package com.frota.config;

import com.frota.model.*;
import com.frota.model.enums.*;
import com.frota.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(
            UsuarioRepository usuarioRepo,
            VeiculoRepository veiculoRepo,
            ManutencaoRepository manutencaoRepo) {
        return args -> {

            if (usuarioRepo.count() == 0) {
                System.out.println("Populando banco de dados inicial...");

                // 1. Criar Usuários
                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setEmail("admin@empresa.com");
                admin.setSenha("123456"); // No futuro usaremos BCrypt
                admin.setPerfil(Perfil.ADMIN);

                Usuario mecanico = new Usuario();
                mecanico.setNome("Oficina do Zé");
                mecanico.setEmail("ze@oficina.com");
                mecanico.setSenha("123456");
                mecanico.setPerfil(Perfil.MECANICO);

                usuarioRepo.saveAll(Arrays.asList(admin, mecanico));

                // 2. Criar Veículos
                Veiculo v1 = new Veiculo();
                v1.setPlaca("ABC1D23");
                v1.setModelo("VW Gol 2023");
                v1.setKmAtual(15000);

                Veiculo v2 = new Veiculo();
                v2.setPlaca("XYZ9G88");
                v2.setModelo("Fiat Toro 2022");
                v2.setKmAtual(45000);

                veiculoRepo.saveAll(Arrays.asList(v1, v2));

                // 3. Criar uma Manutenção para teste
                Manutencao m1 = new Manutencao();
                m1.setVeiculo(v1);
                m1.setDescricaoProblema("Barulho na suspensão dianteira e troca de óleo.");
                m1.setStatus(StatusManutencao.ABERTA);

                manutencaoRepo.save(m1);

                System.out.println("Banco de dados populado com sucesso!");
            }
        };
    }
}