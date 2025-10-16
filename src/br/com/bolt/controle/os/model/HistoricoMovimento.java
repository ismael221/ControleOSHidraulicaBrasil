package br.com.bolt.controle.os.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class HistoricoMovimento {

    private BigDecimal idOs;
    private Timestamp dtNeg;
    private String tipMov;
    private BigDecimal codTipoOper;
    private BigDecimal nunota;
    private Double vlrNota;

    public HistoricoMovimento(BigDecimal idOs, Timestamp dtNeg, String tipMov, BigDecimal codTipoOper, BigDecimal nunota, Double vlrNota) {
        this.idOs = idOs;
        this.dtNeg = dtNeg;
        this.tipMov = tipMov;
        this.codTipoOper = codTipoOper;
        this.nunota = nunota;
        this.vlrNota = vlrNota;
    }

    public HistoricoMovimento(BigDecimal idOs, Timestamp dtNeg, String tipMov, BigDecimal codTipoOper, Double vlrNota) {
        this.idOs = idOs;
        this.dtNeg = dtNeg;
        this.tipMov = tipMov;
        this.codTipoOper = codTipoOper;
        this.vlrNota = vlrNota;
    }

    public BigDecimal getIdOs() {
        return idOs;
    }

    public void setIdOs(BigDecimal idOs) {
        this.idOs = idOs;
    }

    public Timestamp getDtNeg() {
        return dtNeg;
    }

    public void setDtNeg(Timestamp dtNeg) {
        this.dtNeg = dtNeg;
    }

    public String getTipMov() {
        return tipMov;
    }

    public void setTipMov(String tipMov) {
        this.tipMov = tipMov;
    }

    public BigDecimal getCodTipoOper() {
        return codTipoOper;
    }

    public void setCodTipoOper(BigDecimal codTipoOper) {
        this.codTipoOper = codTipoOper;
    }

    public Double getVlrNota() {
        return vlrNota;
    }

    public void setVlrNota(Double vlrNota) {
        this.vlrNota = vlrNota;
    }

    public BigDecimal getNunota() {
        return nunota;
    }

    public void setNunota(BigDecimal nunota) {
        this.nunota = nunota;
    }

    @Override
    public String toString() {
        return "HistoricoMovimento{" +
                "idOs=" + idOs +
                ", dtNeg=" + dtNeg +
                ", tipMov='" + tipMov + '\'' +
                ", codTipoOper=" + codTipoOper +
                ", nunota=" + nunota +
                ", vlrNota=" + vlrNota +
                '}';
    }
}
