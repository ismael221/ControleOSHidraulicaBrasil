package br.com.bolt.controle.os.view;

import br.com.bolt.controle.os.model.OrdemDeServico;
import br.com.bolt.controle.os.repository.OrdemDeServicoRepository;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;
import java.util.List;

public class EventGerarOs implements EventoProgramavelJava {

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
        DynamicVO cabecalhoVO = (DynamicVO) event.getVo();
        OrdemDeServicoRepository ordemDeServicoRepository = new OrdemDeServicoRepository();
        BigDecimal nunota = cabecalhoVO.asBigDecimal("NUNOTA");
        BigDecimal codParc = cabecalhoVO.asBigDecimal("CODPARC");
        String statusNota = cabecalhoVO.asString("STATUSNOTA");
        String descricao = cabecalhoVO.asString("OBSERVACAO");

        if (statusNota.equals("L")) {
            List<OrdemDeServico> ordens = ordemDeServicoRepository.gerarOrdensDeServico(nunota);

            System.out.println("Ordem de servicos gerados com sucesso");

            ordens.forEach(ordemDeServico -> {
                ordemDeServico.setParceiro(codParc);
                ordemDeServico.setDescricaoProblema(descricao);
                ordemDeServicoRepository.salvarOrdensDeServico(ordemDeServico);
                System.out.println(ordemDeServico.toString());
            });
        }

    }

    @Override
    public void afterDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext tranCtx) throws Exception {

    }
}
