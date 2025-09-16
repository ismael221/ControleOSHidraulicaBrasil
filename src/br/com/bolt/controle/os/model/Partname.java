package br.com.bolt.controle.os.model;

import java.math.BigDecimal;

public class Partname {

    public BigDecimal partname;
    public BigDecimal ordem;
    public BigDecimal quantidade;

    public Partname(BigDecimal partname, BigDecimal ordem, BigDecimal quantidade) {
        this.partname = partname;
        this.ordem = ordem;
        this.quantidade = quantidade;
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

    @Override
    public String toString() {
        return "Partname{" +
                "partname=" + partname +
                ", ordem=" + ordem +
                ", quantidade=" + quantidade +
                '}';
    }
}
