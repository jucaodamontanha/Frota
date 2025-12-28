package com.frota.service;


import com.frota.dto.OrcamentoRequestDTO;
import com.frota.model.Manutencao;
import com.frota.model.Orcamento;
import com.frota.model.enums.StatusManutencao;
import com.frota.model.enums.StatusOrcamento;
import com.frota.repository.ManutencaoRepository;
import com.frota.repository.OrcamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository repository;
    @Autowired private ManutencaoRepository manutencaoRepository;

    @Transactional
    public Orcamento salvar(OrcamentoRequestDTO dto, String nomeArquivo) {
        Manutencao m = manutencaoRepository.findById(dto.manutencaoId())
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));

        Orcamento orc = new Orcamento();
        orc.setManutencao(m);
        orc.setValorTotal(dto.valorTotal());
        orc.setArquivoUrl(nomeArquivo);
        orc.setStatus(StatusOrcamento.PENDENTE);

        // Muda status da manutenção para indicar que já tem orçamento
        m.setStatus(StatusManutencao.EM_ORCAMENTO);

        return repository.save(orc);
    }

    @Transactional
    public void aprovar(Long orcamentoId) {
        Orcamento orc = repository.findById(orcamentoId).get();
        orc.setStatus(StatusOrcamento.APROVADO);

        // REGRA DE OURO: Se aprovou o orçamento, o carro entra em execução
        orc.getManutencao().setStatus(StatusManutencao.EM_EXECUCAO);

        repository.save(orc);
    }
}