package br.com.bolt.controle.os.model;

import java.math.BigDecimal;
import java.util.List;

public class NotaFiscal {
    public BigDecimal nunota;
    public String descricao;
    public BigDecimal numNota;
    public BigDecimal codEmp;
    public BigDecimal codParc;
    public BigDecimal codNat;
    public BigDecimal codTipOper;
    public String tipMov;
    public Double vlrNota;
    public BigDecimal codCenCus;
    public List<ItemNota> itemNotas;

    public BigDecimal getNunota() {
        return nunota;
    }

    public void setNunota(BigDecimal nunota) {
        this.nunota = nunota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getNumNota() {
        return numNota;
    }

    public void setNumNota(BigDecimal numNota) {
        this.numNota = numNota;
    }

    public BigDecimal getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(BigDecimal codEmp) {
        this.codEmp = codEmp;
    }

    public BigDecimal getCodParc() {
        return codParc;
    }

    public void setCodParc(BigDecimal codParc) {
        this.codParc = codParc;
    }

    public BigDecimal getCodTipOper() {
        return codTipOper;
    }

    public void setCodTipOper(BigDecimal codTipOper) {
        this.codTipOper = codTipOper;
    }

    public String getTipMov() {
        return tipMov;
    }

    public void setTipMov(String tipMov) {
        this.tipMov = tipMov;
    }

    public Double getVlrNota() {
        return vlrNota;
    }

    public void setVlrNota(Double vlrNota) {
        this.vlrNota = vlrNota;
    }

    public BigDecimal getCodCenCus() {
        return codCenCus;
    }

    public void setCodCenCus(BigDecimal codCenCus) {
        this.codCenCus = codCenCus;
    }

    public List<ItemNota> getItemNotas() {
        return itemNotas;
    }

    public void setItemNotas(List<ItemNota> itemNotas) {
        this.itemNotas = itemNotas;
    }

    public BigDecimal getCodNat() {
        return codNat;
    }

    public void setCodNat(BigDecimal codNat) {
        this.codNat = codNat;
    }

    @Override
    public String toString() {
        return "NotaFiscal{" +
                "nunota=" + nunota +
                ", descricao='" + descricao + '\'' +
                ", numNota=" + numNota +
                ", codEmp=" + codEmp +
                ", codParc=" + codParc +
                ", codNat=" + codNat +
                ", codTipOper=" + codTipOper +
                ", tipMov='" + tipMov + '\'' +
                ", vlrNota=" + vlrNota +
                ", codCenCus=" + codCenCus +
                ", itemNotas=" + itemNotas +
                '}';
    }
}
