package br.com.bolt.controle.os.model;

import java.math.BigDecimal;

public class Componente {

    public BigDecimal codProd;
    public BigDecimal codComponente;
    public BigDecimal partname;
    public BigDecimal quantidade;
    public String unidade;

    public BigDecimal getPartname() {
        return partname;
    }

    public void setPartname(BigDecimal partname) {
        this.partname = partname;
    }

    public BigDecimal getCodComponente() {
        return codComponente;
    }

    public void setCodComponente(BigDecimal codComponente) {
        this.codComponente = codComponente;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
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
                ", partname=" + partname +
                ", quantidade=" + quantidade +
                ", unidade='" + unidade + '\'' +
                '}';
    }
}
