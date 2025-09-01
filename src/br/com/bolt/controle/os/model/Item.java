package br.com.bolt.controle.os.model;

import java.math.BigDecimal;

public class Item {

    public BigDecimal nunota;
    public BigDecimal macroGrupo;
    public BigDecimal oficina;
    public BigDecimal quantidade;
    public BigDecimal preco;

    public BigDecimal getNunota() {
        return nunota;
    }

    public void setNunota(BigDecimal nunota) {
        this.nunota = nunota;
    }

    public BigDecimal getMacroGrupo() {
        return macroGrupo;
    }

    public void setMacroGrupo(BigDecimal macroGrupo) {
        this.macroGrupo = macroGrupo;
    }

    public BigDecimal getOficina() {
        return oficina;
    }

    public void setOficina(BigDecimal oficina) {
        this.oficina = oficina;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nunota=" + nunota +
                ", macroGrupo=" + macroGrupo +
                ", oficina=" + oficina +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}
