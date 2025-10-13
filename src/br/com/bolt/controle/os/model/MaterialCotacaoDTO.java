package br.com.bolt.controle.os.model;

import java.math.BigDecimal;

public class MaterialCotacaoDTO {

    private String nuos;
    private BigDecimal id;
    private BigDecimal codProd;
    private BigDecimal codPartname;
    private BigDecimal quantidade;
    private String descr;
    private String unidade;


    public BigDecimal getCodPartname() {
        return codPartname;
    }

    public void setCodPartname(BigDecimal codPartname) {
        this.codPartname = codPartname;
    }

    public String getNuos() {
        return nuos;
    }

    public void setNuos(String nuos) {
        this.nuos = nuos;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getCodProd() {
        return codProd;
    }

    public void setCodProd(BigDecimal codProd) {
        this.codProd = codProd;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
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

    @Override
    public String toString() {
        return "MaterialCotacaoDTO{" +
                "nuos='" + nuos + '\'' +
                ", id=" + id +
                ", codProd=" + codProd +
                ", quantidade=" + quantidade +
                ", descr='" + descr + '\'' +
                ", unidade='" + unidade + '\'' +
                '}';
    }
}
