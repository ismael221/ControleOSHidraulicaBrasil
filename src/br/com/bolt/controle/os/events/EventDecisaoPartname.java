package br.com.bolt.controle.os.events;

import br.com.bolt.controle.os.enums.StatusOS;
import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.repository.ServicosRepository;
import br.com.bolt.controle.os.service.ServicosService;
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
        BigDecimal decisao = new BigDecimal(partnameVO.asString("DECISAO"));
        BigDecimal os = partnameVO.asBigDecimal("ID");
        BigDecimal codPartname = partnameVO.asBigDecimal("CODPARTNAME");

        BigDecimal substituicao = new BigDecimal(24);


        ServicosService servicosService = new ServicosService();
        ControleOsRepository controleOsRepository = new ControleOsRepository();

        if (decisao != null && decisao.compareTo(new BigDecimal(2)) == 0) {
            System.out.println("Decisao: " + decisao);
            System.out.println("OS: " + os);
            System.out.println("CodPartname: " + codPartname);
            servicosService.inserirServico(os, codPartname, substituicao);
        }

        if (decisao != null && decisao.compareTo(new BigDecimal(1)) == 0) {
            System.out.println("Decisao: " + decisao);
            System.out.println("OS: " + os);
            System.out.println("CodPartname: " + codPartname);
            controleOsRepository.atualizarStatusOSByPK(os, StatusOS.PERITAGEM_EM_ANDAMENTO.getCodigo());
        }

    }

    @Override
    public void afterDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext tranCtx) throws Exception {

    }
}
