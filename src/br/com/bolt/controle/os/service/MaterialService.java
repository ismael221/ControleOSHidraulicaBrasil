package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.repository.MaterialRepository;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.core.JapeSession;

import java.math.BigDecimal;

public class MaterialService {

    JapeSession.SessionHandle hnd = null;

    private final MaterialRepository materialRepository = new MaterialRepository();

    public void inserirMaterial(BigDecimal codOs, BigDecimal codPartname, BigDecimal codServ, String descricao) {
        try {
            hnd = JapeSession.open();
            materialRepository.lancarMaterial(codOs, codPartname, codServ, descricao);
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }
}
