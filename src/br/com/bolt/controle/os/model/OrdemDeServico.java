package br.com.bolt.controle.os.model;

import java.math.BigDecimal;

public class OrdemDeServico {
    public BigDecimal oficina;
    public BigDecimal empresa;
    public BigDecimal parceiro;
    public BigDecimal statusOS;
    public BigDecimal macrogrupo;
    public BigDecimal orcamento;
    public BigDecimal revisao;
    public String descricaoProblema;
    public String descricaoParaNota;

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getDescricaoParaNota() {
        return descricaoParaNota;
    }

    public void setDescricaoParaNota(String descricaoParaNota) {
        this.descricaoParaNota = descricaoParaNota;
    }

    public BigDecimal getOficina() {
        return oficina;
    }

    public void setOficina(BigDecimal oficina) {
        this.oficina = oficina;
    }

    public BigDecimal getEmpresa() {
        return empresa;
    }

    public void setEmpresa(BigDecimal empresa) {
        this.empresa = empresa;
    }

    public BigDecimal getParceiro() {
        return parceiro;
    }

    public void setParceiro(BigDecimal parceiro) {
        this.parceiro = parceiro;
    }

    public BigDecimal getStatusOS() {
        return statusOS;
    }

    public void setStatusOS(BigDecimal statusOS) {
        this.statusOS = statusOS;
    }

    public BigDecimal getMacrogrupo() {
        return macrogrupo;
    }

    public void setMacrogrupo(BigDecimal macrogrupo) {
        this.macrogrupo = macrogrupo;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(BigDecimal orcamento) {
        this.orcamento = orcamento;
    }

    public BigDecimal getRevisao() {
        return revisao;
    }

    public void setRevisao(BigDecimal revisao) {
        this.revisao = revisao;
    }

    @Override
    public String toString() {
        return "OrdemDeServico{" +
                "oficina=" + oficina +
                ", empresa=" + empresa +
                ", parceiro=" + parceiro +
                ", statusOS=" + statusOS +
                ", macrogrupo=" + macrogrupo +
                ", orcamento=" + orcamento +
                ", revisao=" + revisao +
                ", descricaoProblema='" + descricaoProblema + '\'' +
                ", descricaoParaNota='" + descricaoParaNota + '\'' +
                '}';
    }
}
