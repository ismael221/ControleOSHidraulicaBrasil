package br.com.bolt.controle.os.model;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class AgrupadoDTO {

    private BigDecimal codPartname;
    private String descr;
    private String unidade;
    private BigDecimal quantidade;
    private Set<String> nuos;

    public BigDecimal getCodPartname() {
        return codPartname;
    }

    public void setCodPartname(BigDecimal codPartname) {
        this.codPartname = codPartname;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Set<String> getNuos() {
        return nuos;
    }

    public void setNuos(Set<String> nuos) {
        this.nuos = nuos;
    }

    public AgrupadoDTO(BigDecimal codPartname, String descr, String unidade,
                       BigDecimal quantidade, Set<String> nuos) {
        this.codPartname = codPartname;
        this.descr = descr;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.nuos = nuos;
    }

    @Override
    public String toString() {
        return "Partname: " + codPartname +
                " | Quantidade total: " + quantidade +
                " | OS: " + String.join(", ", nuos);
    }
}
