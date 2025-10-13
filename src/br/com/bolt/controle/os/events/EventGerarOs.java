package br.com.bolt.controle.os.events;

import br.com.bolt.controle.os.model.HistoricoOs;
import br.com.bolt.controle.os.model.OrdemDeServico;
import br.com.bolt.controle.os.repository.HistoricoOsRepository;
import br.com.bolt.controle.os.repository.OrdemDeServicoRepository;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
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
        System.out.println("EventGerarOs.afterUpdate");
        DynamicVO cabecalhoVO = (DynamicVO) event.getVo();
        OrdemDeServicoRepository ordemDeServicoRepository = new OrdemDeServicoRepository();
        HistoricoOsRepository historicoOsRepository = new HistoricoOsRepository();
        BigDecimal nunota = cabecalhoVO.asBigDecimal("NUNOTA");
        BigDecimal codParc = cabecalhoVO.asBigDecimal("CODPARC");
        String statusNota = cabecalhoVO.asString("STATUSNOTA");
        String descricao = cabecalhoVO.asString("OBSERVACAO");
        BigDecimal numNota = cabecalhoVO.asBigDecimal("NUMNOTA");
        BigDecimal codtipOper = cabecalhoVO.asBigDecimal("CODTIPOPER");
        String chassi = cabecalhoVO.asString("AD_CHASSI");
        String frota = cabecalhoVO.asString("AD_NRFROTA");
        String nuPedido = cabecalhoVO.asString("NUMPEDIDO2");
        BigDecimal equipamento = cabecalhoVO.asBigDecimal("AD_IDEQUIP");
        BigDecimal vendedor = cabecalhoVO.asBigDecimal("CODVEND");

        System.out.println("Vai gerar OS no afterUpdate");
        System.out.println("Nunota: " + nunota);
        System.out.println("CodParc: " + codParc);
        System.out.println("StatusNota: " + statusNota);
        System.out.println("Descricao: " + descricao);
        System.out.println("NumNota: " + numNota);
        System.out.println("Chassi: " + chassi);
        System.out.println("Frota: " + frota);
        System.out.println("NuPedido: " + nuPedido);
        System.out.println("Equipamento: " + equipamento);
        System.out.println("Vendedor: " + vendedor);


        if (numNota != null && numNota.compareTo(BigDecimal.ZERO) > 0 && codtipOper.compareTo(new BigDecimal("1000")) == 0) {
            List<OrdemDeServico> ordens = ordemDeServicoRepository.gerarOrdensDeServico(nunota);

            System.out.println("Ordem de servicos gerados com sucesso");

            ordens.forEach(ordemDeServico -> {
                ordemDeServico.setParceiro(codParc);
                ordemDeServico.setDescricaoProblema(descricao);
                ordemDeServico.setChassi(chassi);
                ordemDeServico.setFrota(frota);
                ordemDeServico.setNumeroPedido(nuPedido);
                ordemDeServico.setEquipamento(equipamento);
                ordemDeServico.setVendedor(vendedor);
                BigDecimal idOs = ordemDeServicoRepository.salvarOrdensDeServico(ordemDeServico);
                HistoricoOs historicoOs = new HistoricoOs();
                historicoOs.setIdOS(idOs);
                historicoOs.setDescricao("OS Gerada, status inicial Entendendo demanda");
                historicoOs.setDtAlter(Timestamp.from(Instant.now()));
                historicoOs.setCodUsu(BigDecimal.ZERO);
                try {
                    historicoOsRepository.salvarHistorico(historicoOs);
                } catch (Exception e) {
                    Utils.logarErro(e);
                }
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
