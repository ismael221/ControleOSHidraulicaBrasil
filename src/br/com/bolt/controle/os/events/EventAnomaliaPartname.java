package br.com.bolt.controle.os.events;

import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.modelcore.MGEModelException;

import java.math.BigDecimal;

public class EventAnomaliaPartname implements EventoProgramavelJava {
    @Override
    public void beforeInsert(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeUpdate(PersistenceEvent event) throws Exception {
        DynamicVO partnameVo = (DynamicVO) event.getVo();

        String complementoAnomalia = partnameVo.asString("COMPLANOMA");
        BigDecimal justAnomalia = partnameVo.asBigDecimal("JUSTIDESCI");
        BigDecimal decisao = partnameVo.asBigDecimal("DECISAO");

        // Validação 1: Anomalia/Justificativa obrigatório para decisões diferentes de 1, 6 ou 7
        if (decisao != null &&
                decisao.compareTo(BigDecimal.ONE) != 0 &&   // 1 = Manter
                decisao.compareTo(new BigDecimal(6)) != 0 && // 6 = Executar
                decisao.compareTo(new BigDecimal(7)) != 0) { // 7 = Não se aplica

            if (justAnomalia == null || justAnomalia.compareTo(BigDecimal.ZERO) == 0) {
                throw new MGEModelException("O campo Anomalia/Justificativa é obrigatório para a decisão selecionada.");
            }
        }

        // Validação 2: Complemento da Anomalia só pode ser preenchido se Anomalia/Justificativa tiver valor
        if (complementoAnomalia != null && !complementoAnomalia.trim().isEmpty()) {
            if (justAnomalia == null || justAnomalia.compareTo(BigDecimal.ZERO) == 0) {
                throw new MGEModelException("O campo Complemento da Anomalia só pode ser preenchido se Anomalia/Justificativa estiver preenchido.");
            }
        }
    }

    @Override
    public void beforeDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterUpdate(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext tranCtx) throws Exception {

    }
}
