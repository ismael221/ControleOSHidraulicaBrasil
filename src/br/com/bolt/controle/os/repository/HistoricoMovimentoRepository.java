package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.HistoricoMovimento;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class HistoricoMovimentoRepository {

    public void salvarMovimento(HistoricoMovimento historicoMovimento) {
        System.out.println("Salvando historico de movimento ...");
        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();
            JapeWrapper histMovDAO = JapeFactory.dao("AD_HISTMOVOS");
            DynamicVO save = histMovDAO.create()
                    .set("ID", historicoMovimento.getIdOs())
                    .set("DTNEG", historicoMovimento.getDtNeg())
                    .set("TIPMOV", historicoMovimento.getTipMov())
                    .set("CODTIPOPER", historicoMovimento.getCodTipoOper())
                    .set("NUNOTA", historicoMovimento.getNunota())
                    .set("VLRNOTA", historicoMovimento.getVlrNota())
                    .save();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }

    }
}
