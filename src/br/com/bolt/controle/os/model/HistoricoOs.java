package br.com.bolt.controle.os.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class HistoricoOs {

    private BigDecimal idOS;
    private BigDecimal codUsu;
    private Timestamp dtAlter;
    private String descricao;

    public BigDecimal getIdOS() {
        return idOS;
    }

    public void setIdOS(BigDecimal idOS) {
        this.idOS = idOS;
    }

    public BigDecimal getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(BigDecimal codUsu) {
        this.codUsu = codUsu;
    }

    public Timestamp getDtAlter() {
        return dtAlter;
    }

    public void setDtAlter(Timestamp dtAlter) {
        this.dtAlter = dtAlter;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "HistoricoOs{" +
                "idOS=" + idOS +
                ", codUsu=" + codUsu +
                ", dtAlter=" + dtAlter +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
