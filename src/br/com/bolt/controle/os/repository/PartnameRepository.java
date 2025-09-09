package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;

import java.math.BigDecimal;

public class PartnameRepository {

    final BigDecimal ORDEM_94 = new BigDecimal(600900);
    final BigDecimal ORDEM_95 = new BigDecimal(601000);
    final BigDecimal ORDEM_96 = new BigDecimal(601100);

    public void lancarPartnamesAoEnviarParaPeritagem(BigDecimal codOs) {
        System.out.println("Lançar 3 partnames...");

        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();
            JapeWrapper parntameDAO = JapeFactory.dao("AD_PARTNAME");

            DynamicVO ordem1 = parntameDAO.create()
                    .set("ID", codOs)
                    .set("ORDEM", 94)
                    .set("QTD", 1)
                    .set("PARTNAME", ORDEM_94)
                    .save();

            DynamicVO ordem2 = parntameDAO.create()
                    .set("ID", codOs)
                    .set("ORDEM", 95)
                    .set("QTD", 1)
                    .set("PARTNAME", ORDEM_95)
                    .save();

            DynamicVO ordem3 = parntameDAO.create()
                    .set("ID", codOs)
                    .set("ORDEM", 96)
                    .set("QTD", 1)
                    .set("PARTNAME", ORDEM_96)
                    .save();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }
}
