package br.com.bolt.controle.os.events;

import br.com.bolt.controle.os.repository.ServicosRepository;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;

public class EventServicosDoPartname implements EventoProgramavelJava {
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
        DynamicVO partnameVO = (DynamicVO) event.getVo();

        ServicosRepository servicosRepository = new ServicosRepository();

        BigDecimal codOs = partnameVO.asBigDecimal("ID");

        BigDecimal partname = partnameVO.asBigDecimal("PARTNAME") != null
                ? partnameVO.asBigDecimal("PARTNAME")
                : BigDecimal.ZERO;

        BigDecimal servico = servicosRepository.consultarServicoRelacionadoPartname(partname) != null
                ? servicosRepository.consultarServicoRelacionadoPartname(partname)
                : BigDecimal.ZERO;

        if (servico.compareTo(BigDecimal.ZERO) > 0) {
            servicosRepository.lancarServico(codOs, partname, servico);
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
