package com.frota.repository;

import com.frota.model.Manutencao;
import com.frota.model.enums.StatusManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    // O Spring já entende que veiculo_id no banco é veiculo.id no Java
    List<Manutencao> findByVeiculoId(Long veiculoId);

    // Busca pelo status da manutenção (ABERTA, EM_EXECUCAO, etc)
    List<Manutencao> findByStatus(StatusManutencao status);

}
