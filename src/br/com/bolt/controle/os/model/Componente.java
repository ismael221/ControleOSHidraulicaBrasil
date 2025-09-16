package br.com.bolt.controle.os.model;

import java.math.BigDecimal;

public class Componente {

    public BigDecimal codProd;
    public BigDecimal codComponente;
    public Double quantidade;
    public String unidade;

    public BigDecimal getCodComponente() {
        return codComponente;
    }

    public void setCodComponente(BigDecimal codComponente) {
        this.codComponente = codComponente;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getCodProd() {
        return codProd;
    }

    public void setCodProd(BigDecimal codProd) {
        this.codProd = codProd;
    }

    @Override
    public String toString() {
        return "Componente{" +
                "codProd=" + codProd +
                ", codComponente=" + codComponente +
                ", quantidade=" + quantidade +
                ", unidade='" + unidade + '\'' +
                '}';
    }
}
