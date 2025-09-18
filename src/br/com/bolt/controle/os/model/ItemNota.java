package br.com.bolt.controle.os.model;

import java.math.BigDecimal;

public class ItemNota {
    public BigDecimal nunota;
    public BigDecimal codEmp;
    public BigDecimal codProd;
    public BigDecimal codLocalOrig;
    public String controle;
    public Double qtdNeg;
    public Double vlrUnit;
    public Double vlrTotal;
    public String descricao;

    public BigDecimal getNunota() {
        return nunota;
    }

    public void setNunota(BigDecimal nunota) {
        this.nunota = nunota;
    }

    public BigDecimal getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(BigDecimal codEmp) {
        this.codEmp = codEmp;
    }

    public BigDecimal getCodProd() {
        return codProd;
    }

    public void setCodProd(BigDecimal codProd) {
        this.codProd = codProd;
    }

    public BigDecimal getCodLocalOrig() {
        return codLocalOrig;
    }

    public void setCodLocalOrig(BigDecimal codLocalOrig) {
        this.codLocalOrig = codLocalOrig;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public Double getQtdNeg() {
        return qtdNeg;
    }

    public void setQtdNeg(Double qtdNeg) {
        this.qtdNeg = qtdNeg;
    }

    public Double getVlrUnit() {
        return vlrUnit;
    }

    public void setVlrUnit(Double vlrUnit) {
        this.vlrUnit = vlrUnit;
    }

    public Double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(Double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ItemNota{" +
                "nunota=" + nunota +
                ", codEmp=" + codEmp +
                ", codProd=" + codProd +
                ", codLocalOrig=" + codLocalOrig +
                ", controle='" + controle + '\'' +
                ", qtdNeg=" + qtdNeg +
                ", vlrUnit=" + vlrUnit +
                ", vlrTotal=" + vlrTotal +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
