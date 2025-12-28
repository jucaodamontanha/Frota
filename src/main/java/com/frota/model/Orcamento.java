package com.frota.model;

import com.frota.model.enums.StatusOrcamento;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orcamentos")
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manutencao_id")
    private Manutencao manutencao;

    @ManyToOne
    @JoinColumn(name = "oficina_id")
    private Usuario oficina; // O mecânico/funileiro logado

    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusOrcamento status = StatusOrcamento.PENDENTE;

    private String arquivoUrl; // Caminho da foto ou PDF do orçamento

    private String motivoReprovacao; // Campo extra para feedback ao mecânico

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

    public Usuario getOficina() {
        return oficina;
    }

    public void setOficina(Usuario oficina) {
        this.oficina = oficina;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusOrcamento getStatus() {
        return status;
    }

    public void setStatus(StatusOrcamento status) {
        this.status = status;
    }

    public String getArquivoUrl() {
        return arquivoUrl;
    }

    public void setArquivoUrl(String arquivoUrl) {
        this.arquivoUrl = arquivoUrl;
    }

    public String getMotivoReprovacao() {
        return motivoReprovacao;
    }

    public void setMotivoReprovacao(String motivoReprovacao) {
        this.motivoReprovacao = motivoReprovacao;
    }
}