package br.com.bolt.controle.os.enums;

import java.math.BigDecimal;
import java.util.Objects;

public enum StatusOS {

    ENTENDENDO_DEMANDA(new BigDecimal(1)),
    AGUARDANDO_PERITAGEM(new BigDecimal(2)),
    PERITAGEM_EM_ANDAMENTO(new BigDecimal(3)),
    COTACAO(new BigDecimal(4)),
    PRECIFICACAO(new BigDecimal(5)),
    NEGOCIACAO(new BigDecimal(6)),
    APROVADA(new BigDecimal(7)),
    FECHADA_APROVADA_EM_OUTRA_REVISAO(new BigDecimal(8)),
    EXECUCAO(new BigDecimal(9)),
    FINALIZADA(new BigDecimal(10)),
    EMITIR_NOTA(new BigDecimal(11)),
    FECHADA(new BigDecimal(12)),
    DEVOLVER(new BigDecimal(13)),
    DEVOLVIDA(new BigDecimal(14));

    private final BigDecimal codigo;

    StatusOS(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public static StatusOS fromCodigo(BigDecimal codigo) {
        for (StatusOS status : StatusOS.values()) {
            if (Objects.equals(status.getCodigo(), codigo)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
