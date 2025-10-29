package br.com.bolt.controle.os.model;

import java.math.BigDecimal;

public class Partname {

    public BigDecimal partname;
    public BigDecimal ordem;
    public BigDecimal codOs;
    public BigDecimal codPartname;
    public BigDecimal quantidade;
    public BigDecimal decisao;
    public BigDecimal codProduto;
    public Double taxaAdicionalServico;
    public Double totalVendaMaterial;
    public Double totalVendaServico;
    public Double custoTotalServico;
    public Double totalVenda;

    public Partname(BigDecimal partname, BigDecimal ordem, BigDecimal quantidade, BigDecimal decisao) {
        this.partname = partname;
        this.ordem = ordem;
        this.quantidade = quantidade;
        this.decisao = decisao;
    }

    public Partname() {
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getOrdem() {
        return ordem;
    }

    public void setOrdem(BigDecimal ordem) {
        this.ordem = ordem;
    }

    public BigDecimal getPartname() {
        return partname;
    }

    public void setPartname(BigDecimal partname) {
        this.partname = partname;
    }

    public BigDecimal getDecisao() {
        return decisao;
    }

    public void setDecisao(BigDecimal decisao) {
        this.decisao = decisao;
    }

    public BigDecimal getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(BigDecimal codProduto) {
        this.codProduto = codProduto;
    }

    public BigDecimal getCodOs() {
        return codOs;
    }

    public void setCodOs(BigDecimal codOs) {
        this.codOs = codOs;
    }

    public BigDecimal getCodPartname() {
        return codPartname;
    }

    public void setCodPartname(BigDecimal codPartname) {
        this.codPartname = codPartname;
    }

    public Double getTaxaAdicionalServico() {
        return taxaAdicionalServico;
    }

    public void setTaxaAdicionalServico(Double taxaAdicionalServico) {
        this.taxaAdicionalServico = taxaAdicionalServico;
    }

    public Double getTotalVendaMaterial() {
        return totalVendaMaterial;
    }

    public void setTotalVendaMaterial(Double totalVendaMaterial) {
        this.totalVendaMaterial = totalVendaMaterial;
    }

    public Double getTotalVendaServico() {
        return totalVendaServico;
    }

    public void setTotalVendaServico(Double totalVendaServico) {
        this.totalVendaServico = totalVendaServico;
    }

    public Double getCustoTotalServico() {
        return custoTotalServico;
    }

    public void setCustoTotalServico(Double custoTotalServico) {
        this.custoTotalServico = custoTotalServico;
    }

    public Double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(Double totalVenda) {
        this.totalVenda = totalVenda;
    }

    @Override
    public String toString() {
        return "Partname{" +
                "partname=" + partname +
                ", ordem=" + ordem +
                ", codOs=" + codOs +
                ", codPartname=" + codPartname +
                ", quantidade=" + quantidade +
                ", decisao=" + decisao +
                ", codProduto=" + codProduto +
                ", taxaAdicionalServico=" + taxaAdicionalServico +
                ", totalVendaMaterial=" + totalVendaMaterial +
                ", totalVendaServico=" + totalVendaServico +
                ", custoTotalServico=" + custoTotalServico +
                ", totalVenda=" + totalVenda +
                '}';
    }
}
