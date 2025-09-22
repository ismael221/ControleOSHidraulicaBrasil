package br.com.bolt.controle.os.model;

import java.math.BigDecimal;

public class Cotacao {
    private BigDecimal partname;
    private BigDecimal codProduto;
    private String descricao;
    private BigDecimal quantidade;
    private Double diameInterno;
    private Double diameExterno;
    private String comprimento;
    private BigDecimal orcamento;
    private String oss;
    private BigDecimal material;
    private String controle;
    private String aprovado;
    private String tipoCotacao;
    private BigDecimal fornecedor;
    private BigDecimal prazoEntrega;
    private Double vlrUnitario;
    private Double vlrTotal;
    private Double margem;
    private Double vlrVendaUnitario;
    private Double vlrVendaTotal;
    private String numeroOS;


    public Cotacao() {
    }

    public Cotacao(BigDecimal partname, BigDecimal codProduto, String descricao, BigDecimal quantidade, Double diameInterno, Double diameExterno, String comprimento, BigDecimal orcamento, String oss, BigDecimal material, String controle, String aprovado, String tipoCotacao, BigDecimal fornecedor, BigDecimal prazoEntrega, Double vlrUnitario, Double vlrTotal, Double margem, Double vlrVendaUnitario, Double vlrVendaTotal, String numeroOS) {
        this.partname = partname;
        this.codProduto = codProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.diameInterno = diameInterno;
        this.diameExterno = diameExterno;
        this.comprimento = comprimento;
        this.orcamento = orcamento;
        this.oss = oss;
        this.material = material;
        this.controle = controle;
        this.aprovado = aprovado;
        this.tipoCotacao = tipoCotacao;
        this.fornecedor = fornecedor;
        this.prazoEntrega = prazoEntrega;
        this.vlrUnitario = vlrUnitario;
        this.vlrTotal = vlrTotal;
        this.margem = margem;
        this.vlrVendaUnitario = vlrVendaUnitario;
        this.vlrVendaTotal = vlrVendaTotal;
        this.numeroOS = numeroOS;
    }

    public BigDecimal getPartname() {
        return partname;
    }

    public void setPartname(BigDecimal partname) {
        this.partname = partname;
    }

    public BigDecimal getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(BigDecimal codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Double getDiameInterno() {
        return diameInterno;
    }

    public void setDiameInterno(Double diameInterno) {
        this.diameInterno = diameInterno;
    }

    public Double getDiameExterno() {
        return diameExterno;
    }

    public void setDiameExterno(Double diameExterno) {
        this.diameExterno = diameExterno;
    }

    public String getComprimento() {
        return comprimento;
    }

    public void setComprimento(String comprimento) {
        this.comprimento = comprimento;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(BigDecimal orcamento) {
        this.orcamento = orcamento;
    }

    public String getOss() {
        return oss;
    }

    public void setOss(String oss) {
        this.oss = oss;
    }

    public BigDecimal getMaterial() {
        return material;
    }

    public void setMaterial(BigDecimal material) {
        this.material = material;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(String aprovado) {
        this.aprovado = aprovado;
    }

    public String getTipoCotacao() {
        return tipoCotacao;
    }

    public void setTipoCotacao(String tipoCotacao) {
        this.tipoCotacao = tipoCotacao;
    }

    public BigDecimal getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(BigDecimal fornecedor) {
        this.fornecedor = fornecedor;
    }

    public BigDecimal getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(BigDecimal prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public Double getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(Double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }

    public Double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(Double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public Double getMargem() {
        return margem;
    }

    public void setMargem(Double margem) {
        this.margem = margem;
    }

    public Double getVlrVendaUnitario() {
        return vlrVendaUnitario;
    }

    public void setVlrVendaUnitario(Double vlrVendaUnitario) {
        this.vlrVendaUnitario = vlrVendaUnitario;
    }

    public Double getVlrVendaTotal() {
        return vlrVendaTotal;
    }

    public void setVlrVendaTotal(Double vlrVendaTotal) {
        this.vlrVendaTotal = vlrVendaTotal;
    }

    public String getNumeroOS() {
        return numeroOS;
    }

    public void setNumeroOS(String numeroOS) {
        this.numeroOS = numeroOS;
    }

    @Override
    public String toString() {
        return "Cotacao{" +
                "partname='" + partname + '\'' +
                ", codProduto=" + codProduto +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", diameInterno=" + diameInterno +
                ", diameExterno=" + diameExterno +
                ", comprimento='" + comprimento + '\'' +
                ", orcamento=" + orcamento +
                ", oss='" + oss + '\'' +
                ", material=" + material +
                ", controle='" + controle + '\'' +
                ", aprovado='" + aprovado + '\'' +
                ", tipoCotacao='" + tipoCotacao + '\'' +
                ", fornecedor=" + fornecedor +
                ", prazoEntrega=" + prazoEntrega +
                ", vlrUnitario=" + vlrUnitario +
                ", vlrTotal=" + vlrTotal +
                ", margem=" + margem +
                ", vlrVendaUnitario=" + vlrVendaUnitario +
                ", vlrVendaTotal=" + vlrVendaTotal +
                ", numeroOS='" + numeroOS + '\'' +
                '}';
    }
}
