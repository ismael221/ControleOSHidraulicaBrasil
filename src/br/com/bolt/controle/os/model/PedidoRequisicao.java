package br.com.bolt.controle.os.model;

import java.math.BigDecimal;
import java.util.List;

public class PedidoRequisicao {

    public BigDecimal codTipOper;
    public BigDecimal codUsu;
    public BigDecimal codEmp;
    public BigDecimal codParc;
    public BigDecimal codTipVenda;
    public BigDecimal codCenCus;
    public BigDecimal codNat;
    public Double vlrTotal;
    public String tipMov;
    public List<ItemNota> itensNota;

    public BigDecimal getCodTipOper() {
        return codTipOper;
    }

    public void setCodTipOper(BigDecimal codTipOper) {
        this.codTipOper = codTipOper;
    }

    public BigDecimal getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(BigDecimal codUsu) {
        this.codUsu = codUsu;
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

    public BigDecimal getCodTipVenda() {
        return codTipVenda;
    }

    public void setCodTipVenda(BigDecimal codTipVenda) {
        this.codTipVenda = codTipVenda;
    }

    public BigDecimal getCodCenCus() {
        return codCenCus;
    }

    public void setCodCenCus(BigDecimal codCenCus) {
        this.codCenCus = codCenCus;
    }

    public BigDecimal getCodNat() {
        return codNat;
    }

    public void setCodNat(BigDecimal codNat) {
        this.codNat = codNat;
    }

    public Double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(Double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public String getTipMov() {
        return tipMov;
    }

    public void setTipMov(String tipMov) {
        this.tipMov = tipMov;
    }

    public List<ItemNota> getItensNota() {
        return itensNota;
    }

    public void setItensNota(List<ItemNota> itensNota) {
        this.itensNota = itensNota;
    }

    @Override
    public String toString() {
        return "PedidoRequisicao{" +
                "codTipOper=" + codTipOper +
                ", codUsu=" + codUsu +
                ", codEmp=" + codEmp +
                ", codParc=" + codParc +
                ", codTipVenda=" + codTipVenda +
                ", codCenCus=" + codCenCus +
                ", codNat=" + codNat +
                ", vlrTotal=" + vlrTotal +
                ", tipMov='" + tipMov + '\'' +
                ", itensNota=" + itensNota +
                '}';
    }
}
