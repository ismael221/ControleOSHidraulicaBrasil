package br.com.bolt.controle.os.view;

import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;

public class EventProgramacaoServico implements EventoProgramavelJava {

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
        System.out.println("EventProgramacaoServico::AfterUpdate");
        ControleOsRepository controleOsRepository = new ControleOsRepository();
        DynamicVO servicoVO = (DynamicVO) event.getVo();
        BigDecimal idOs = servicoVO.asBigDecimal("ID");
        String status = servicoVO.asString("STATUS");


        if (idOs != null && status != null) {
            if (status.equals("P")){
                controleOsRepository.atualizarStatusOSByPK(idOs, new BigDecimal(9));
            }
        }

    }

    @Override
    public void afterDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext tranCtx) throws Exception {

    }
}
