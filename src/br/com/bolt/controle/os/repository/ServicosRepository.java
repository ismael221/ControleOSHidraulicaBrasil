package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;

import java.math.BigDecimal;

public class ServicosRepository {

    private final BigDecimal substituicao = new BigDecimal(24);

    public void lancarPartnamesAoEnviarParaPeritagem(BigDecimal codOs, BigDecimal codPartname) {
        System.out.println("Lançando serviço de substituição...");

        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();
            JapeWrapper parntameDAO = JapeFactory.dao("AD_SERVICOS");

            DynamicVO save = parntameDAO.create()
                    .set("ID", codOs)
                    .set("CODPARTNAME", codPartname)
                    .set("CODPROD", substituicao)
                    .set("QTD", BigDecimal.ONE)
                    .save();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }
}
