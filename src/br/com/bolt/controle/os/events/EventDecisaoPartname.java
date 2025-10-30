package br.com.bolt.controle.os.events;

import br.com.bolt.controle.os.enums.StatusOS;
import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.repository.PartnameRepository;
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
        BigDecimal decisao = partnameVO.asBigDecimal("DECISAO");
        BigDecimal os = partnameVO.asBigDecimal("ID");
        BigDecimal codPartname = partnameVO.asBigDecimal("CODPARTNAME");
        String importado = partnameVO.asString("IMPORTASERVICO");
        BigDecimal substituicao = new BigDecimal(24);


        ServicosService servicosService = new ServicosService();
        ControleOsRepository controleOsRepository = new ControleOsRepository();
        PartnameRepository partnameRepository = new PartnameRepository();

        if (decisao != null && decisao.compareTo(new BigDecimal(2)) == 0 && (importado == null || importado.equals("N"))) {
            System.out.println("Decisao: " + decisao);
            System.out.println("OS: " + os);
            System.out.println("CodPartname: " + codPartname);
            servicosService.inserirServico(os, codPartname, substituicao);
            partnameRepository.atualizarStatusInsercaoPartname(os, codPartname);
        }

        if (decisao != null) {
            System.out.println("Decisão atual: " + decisao);
            if (decisao.compareTo(BigDecimal.ONE) > 0) {
                System.out.println("Executando atualização da OS...");
                controleOsRepository.atualizarStatusOSByPK(os, StatusOS.PERITAGEM_EM_ANDAMENTO.getCodigo());
            } else {
                System.out.println("Decisão <= 1, nenhuma ação executada.");
            }
        } else {
            System.out.println("Decisão é null!");
        }


    }

    @Override
    public void afterDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext tranCtx) throws Exception {

    }
}
