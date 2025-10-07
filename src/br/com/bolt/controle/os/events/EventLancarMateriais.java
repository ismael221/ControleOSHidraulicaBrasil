package br.com.bolt.controle.os.events;

import br.com.bolt.controle.os.repository.MaterialRepository;
import br.com.bolt.controle.os.service.MaterialService;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;

public class EventLancarMateriais implements EventoProgramavelJava {

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
        System.out.println("EventLancarMateriais::afterInsert");
        DynamicVO servicoVo = (DynamicVO) event.getVo();
        MaterialService materialService = new MaterialService();
        BigDecimal codOs = servicoVo.asBigDecimal("ID") != null ? servicoVo.asBigDecimal("ID") : BigDecimal.ZERO;
        BigDecimal codServ = servicoVo.asBigDecimal("CODSERV") != null ? servicoVo.asBigDecimal("CODSERV") : BigDecimal.ZERO;
        BigDecimal codPartname = servicoVo.asBigDecimal("CODPARTNAME") != null ? servicoVo.asBigDecimal("CODPARTNAME") : BigDecimal.ZERO;
        BigDecimal codProduto = servicoVo.asBigDecimal("CODPROD") != null ? servicoVo.asBigDecimal("CODPROD") : BigDecimal.ZERO;
        String descricao = "Peça para substituir o partname []";

        if (codProduto.compareTo(new BigDecimal(24)) == 0) {
            materialService.inserirMaterial(codOs, codPartname, codServ, descricao);
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
