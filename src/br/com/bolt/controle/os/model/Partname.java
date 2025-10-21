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

    public Partname(BigDecimal partname, BigDecimal ordem, BigDecimal quantidade, BigDecimal decisao) {
        this.partname = partname;
        this.ordem = ordem;
        this.quantidade = quantidade;
        this.decisao = decisao;
    }

    public Partname() {}

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
                '}';
    }
}
