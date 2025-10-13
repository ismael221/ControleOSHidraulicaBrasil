package br.com.bolt.controle.os.events;

import br.com.bolt.controle.os.service.CotacaoService;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;

public class EventGerarCotacoes implements EventoProgramavelJava {
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
        DynamicVO cotacaoVO = (DynamicVO) event.getVo();
        BigDecimal nunota = cotacaoVO.asBigDecimal("NUNOTA");
        BigDecimal codCota = cotacaoVO.asBigDecimal("CODCOT");
        System.out.println("COTACAO: " + codCota);
        System.out.println("NUNOTA: " + nunota);
        CotacaoService cotacaoService = new CotacaoService();

        if (nunota != null) {
            cotacaoService.inserirMaterialCotacoes(nunota, codCota);
        }

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
