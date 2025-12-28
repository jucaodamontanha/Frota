package com.frota.repository;

import com.frota.model.Orcamento;
import com.frota.model.enums.StatusOrcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Exemplo para o Repositório de Orçamentos
@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    // Busca todos os orçamentos de uma manutenção específica
    List<Orcamento> findByManutencaoId(Long manutencaoId);

    // Busca orçamentos por status (ex: para o gestor ver o que está PENDENTE)
    List<Orcamento> findByStatus(StatusOrcamento status);
}