package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.repository.ServicosRepository;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.core.JapeSession;

import java.math.BigDecimal;

public class ServicosService {

    JapeSession.SessionHandle hnd = null;

    private final ServicosRepository servicosRepository = new ServicosRepository();

    public void inserirServico(BigDecimal codOs, BigDecimal codPartname, BigDecimal codProd) {
        try {
            hnd = JapeSession.open();
            servicosRepository.lancarServico(codOs, codPartname, codProd);
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }
}
