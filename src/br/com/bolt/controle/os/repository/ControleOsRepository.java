package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.wrapper.JapeFactory;

import java.math.BigDecimal;

public class ControleOsRepository {

    public void atualizandoNumOsByPK(BigDecimal codOS, String numOs) {
        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();

            JapeFactory.dao("AD_CONTROLEOS").
                    prepareToUpdateByPK(codOS)
                    .set("NUOS", numOs)
                    .update();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

    public void atualizarStatusOSByPK(BigDecimal codOS, BigDecimal status) {
        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();

            JapeFactory.dao("AD_CONTROLEOS")
                    .prepareToUpdateByPK(codOS)
                    .set("STATUS", status)
                    .update();
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }
}
