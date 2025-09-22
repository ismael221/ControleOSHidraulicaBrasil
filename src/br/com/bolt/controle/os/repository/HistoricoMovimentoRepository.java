package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.HistoricoMovimento;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class HistoricoMovimentoRepository {

    public void salvarMovimento(HistoricoMovimento historicoMovimento) {
        System.out.println("Salvando historico de movimento ...");

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        JapeSession.SessionHandle hnd = null;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("INSERT INTO AD_HISTMOVOS (ID, DTNEG, TIPMOV, CODTIPOPER, VLRNOTA) VALUES (:ID, :DTNEG, :TIPMOV, :CODTIPOPER, :VLRNOTA)");
            sql.setNamedParameter("ID", historicoMovimento.getIdOs());
            sql.setNamedParameter("DTNGE", historicoMovimento.getDtNeg());
            sql.setNamedParameter("TIPMOV", historicoMovimento.getTipMov());
            sql.setNamedParameter("CODTIPOOPER", historicoMovimento.getCodTipoOper());
            sql.setNamedParameter("VLRNOTA", historicoMovimento.getVlrNota());

            sql.executeUpdate();
            sql.executeUpdate("COMMIT");

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }
    }
}
