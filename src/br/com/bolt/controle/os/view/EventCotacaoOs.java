package br.com.bolt.controle.os.view;

import br.com.bolt.controle.os.repository.CotacaoRepository;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;

public class EventCotacaoOs implements EventoProgramavelJava {
    @Override
    public void beforeInsert(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeUpdate(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterUpdate(PersistenceEvent event) throws Exception {
        DynamicVO ordemServicoVO = (DynamicVO) event.getVo();
        BigDecimal status = ordemServicoVO.asBigDecimal("STATUS");
        CotacaoRepository cotacaoRepository = new CotacaoRepository();

        if (status != null && status.compareTo(new BigDecimal(4)) == 0) {

            //TODO inserir na tela de cotação de OS
        }
    }

    @Override
    public void afterDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext tranCtx) throws Exception {

    }
}
