package br.com.bolt.controle.os.view;

import br.com.bolt.controle.os.model.Cotacao;
import br.com.bolt.controle.os.repository.CotacaoRepository;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;
import java.util.ArrayList;

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
        System.out.println("EventCotacaoOS::AfterUpdate");
        DynamicVO ordemServicoVO = (DynamicVO) event.getVo();
        BigDecimal status = ordemServicoVO.asBigDecimal("STATUS");
        BigDecimal idOs = ordemServicoVO.asBigDecimal("ID");
        CotacaoRepository cotacaoRepository = new CotacaoRepository();

        if (status != null && status.compareTo(new BigDecimal(4)) == 0) {
            System.out.println("Gerando Cotação ...");
            ArrayList<Cotacao> cotacoes = cotacaoRepository.encontrarCotacoes(idOs);

            for (Cotacao cotacao : cotacoes) {
                System.out.println("Cotação gerada: " + cotacao.toString());
                cotacaoRepository.salvarCotacao(cotacao);
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
