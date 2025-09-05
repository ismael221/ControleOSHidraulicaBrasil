package br.com.bolt.controle.os.model;

import java.math.BigDecimal;

public class OrdemDeServico {
    public String nuOS;
    public BigDecimal oficina;
    public BigDecimal empresa;
    public BigDecimal equipamento;
    public BigDecimal codPlaqueta;
    public BigDecimal codPartNum;
    public BigDecimal complexidade;
    public String localizacao;
    public BigDecimal nunota;
    public String prazoOficina;
    public String prazoCompras;
    public String prevEntregaItem;
    public String prevEntregaPeca;
    public BigDecimal parceiro;
    public BigDecimal statusOS;
    public BigDecimal macrogrupo;
    public BigDecimal orcamento;
    public BigDecimal revisao;
    public String descricaoProblema;
    public String descricaoParaNota;

    public String getPrevEntregaPeca() {
        return prevEntregaPeca;
    }

    public void setPrevEntregaPeca(String prevEntregaPeca) {
        this.prevEntregaPeca = prevEntregaPeca;
    }

    public String getPrevEntregaItem() {
        return prevEntregaItem;
    }

    public void setPrevEntregaItem(String prevEntregaItem) {
        this.prevEntregaItem = prevEntregaItem;
    }

    public String getPrazoCompras() {
        return prazoCompras;
    }

    public void setPrazoCompras(String prazoCompras) {
        this.prazoCompras = prazoCompras;
    }

    public String getPrazoOficina() {
        return prazoOficina;
    }

    public void setPrazoOficina(String prazoOficina) {
        this.prazoOficina = prazoOficina;
    }

    public BigDecimal getNunota() {
        return nunota;
    }

    public void setNunota(BigDecimal nunota) {
        this.nunota = nunota;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public BigDecimal getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(BigDecimal complexidade) {
        this.complexidade = complexidade;
    }

    public BigDecimal getCodPartNum() {
        return codPartNum;
    }

    public void setCodPartNum(BigDecimal codPartNum) {
        this.codPartNum = codPartNum;
    }

    public BigDecimal getCodPlaqueta() {
        return codPlaqueta;
    }

    public void setCodPlaqueta(BigDecimal codPlaqueta) {
        this.codPlaqueta = codPlaqueta;
    }

    public BigDecimal getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(BigDecimal equipamento) {
        this.equipamento = equipamento;
    }

    public String getNuOS() {
        return nuOS;
    }

    public void setNuOS(String nuOS) {
        this.nuOS = nuOS;
    }

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
                "nuOS='" + nuOS + '\'' +
                ", oficina=" + oficina +
                ", empresa=" + empresa +
                ", equipamento=" + equipamento +
                ", codPlaqueta=" + codPlaqueta +
                ", codPartNum=" + codPartNum +
                ", complexidade=" + complexidade +
                ", localizacao='" + localizacao + '\'' +
                ", nunota=" + nunota +
                ", prazoOficina='" + prazoOficina + '\'' +
                ", prazoCompras='" + prazoCompras + '\'' +
                ", prevEntregaItem='" + prevEntregaItem + '\'' +
                ", prevEntregaPeca='" + prevEntregaPeca + '\'' +
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
