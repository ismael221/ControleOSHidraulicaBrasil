package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.OrdemDeServico;
import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.core.JapeSession;

public class ControleOsService {

    JapeSession.SessionHandle hnd = null;

    private final ControleOsRepository controleOsRepository = new ControleOsRepository();

    public void criarNovaOs(OrdemDeServico ordemDeServico) {
        try {
            hnd = JapeSession.open();
            controleOsRepository.criarNovaOs(ordemDeServico);
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

}
