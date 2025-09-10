package br.com.bolt.controle.os.view;

import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.repository.ServicosRepository;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;

public class EventDecisaoPartname implements EventoProgramavelJava {
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
        System.out.println("EventDecisaoPartname.afterUpdate");
        DynamicVO partnameVO = (DynamicVO) event.getVo();
        BigDecimal decisao = partnameVO.asBigDecimal("DECISAO");
        BigDecimal os = partnameVO.asBigDecimal("ID");
        BigDecimal codPartname = partnameVO.asBigDecimal("CODPARTNAME");

        System.out.println("Decisao: " + decisao);
        System.out.println("OS: " + os);
        System.out.println("CodPartname: " + codPartname);

        ServicosRepository servicosRepository = new ServicosRepository();
        ControleOsRepository controleOsRepository = new ControleOsRepository();

        if (decisao != null && decisao.compareTo(new BigDecimal(2)) == 0) {
            servicosRepository.lancarPartnamesAoEnviarParaPeritagem(os, codPartname);
        }

        if (os != null && os.compareTo(new BigDecimal(1)) == 0) {
            controleOsRepository.atualizarStatusOSByPK(os, new BigDecimal(3));
        }

    }

    @Override
    public void afterDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext tranCtx) throws Exception {

    }
}
