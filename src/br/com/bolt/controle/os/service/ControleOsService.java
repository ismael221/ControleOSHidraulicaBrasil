package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.HistoricoOs;
import br.com.bolt.controle.os.model.OrdemDeServico;
import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.repository.HistoricoOsRepository;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.core.JapeSession;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

public class ControleOsService {

    JapeSession.SessionHandle hnd = null;

    private final ControleOsRepository controleOsRepository = new ControleOsRepository();
    private final HistoricoOsRepository historicoOsRepository = new HistoricoOsRepository();

    public void criarNovaOs(OrdemDeServico ordemDeServico) {
        BigDecimal codOs = BigDecimal.ZERO;
        try {
            hnd = JapeSession.open();
            codOs = controleOsRepository.criarNovaOs(ordemDeServico);
            if (codOs.compareTo(BigDecimal.ZERO) > 0) {
                HistoricoOs historicoOs = new HistoricoOs();
                historicoOs.setIdOS(codOs);
                historicoOs.setDescricao("OS Gerada, status inicial Entendendo demanda");
                historicoOs.setDtAlter(Timestamp.from(Instant.now()));
                historicoOs.setCodUsu(BigDecimal.ZERO);
                historicoOsRepository.salvarHistorico(historicoOs);

            }
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

}
