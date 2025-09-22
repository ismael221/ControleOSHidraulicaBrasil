package br.com.bolt.controle.os.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ChaveAgrupamento {
    private BigDecimal nunota;
    private BigDecimal codProduto;

    public ChaveAgrupamento(BigDecimal nunota, BigDecimal codProduto) {
        this.nunota = nunota;
        this.codProduto = codProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChaveAgrupamento)) return false;
        ChaveAgrupamento that = (ChaveAgrupamento) o;
        return Objects.equals(nunota, that.nunota) &&
                Objects.equals(codProduto, that.codProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nunota, codProduto);
    }
}
