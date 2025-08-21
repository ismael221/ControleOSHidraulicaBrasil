package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.HistoricoOs;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import com.sankhya.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;

public class HistoricoOsRepository {

    public void salvarHistorico(HistoricoOs historicoOs) throws MGEModelException {
        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();
            JapeWrapper historicoDAO = JapeFactory.dao("AD_HISTSTATUS");

            historicoDAO.create()
                    .set("ID", historicoOs.getIdOS())
                    .set("CODUSU", historicoOs.getCodUsu())
                    .set("DTALTER", historicoOs.getDtAlter())
                    .set("DESCR", historicoOs.getDescricao())
                    .save();


        } catch (Exception e) {
            MGEModelException.throwMe(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

    public HistoricoOs consultarHistorico(BigDecimal numos) throws MGEModelException {

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        HistoricoOs historicoOs = new HistoricoOs();

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT * \n" +
                    "FROM AD_HISTSTATUS \n" +
                    "WHERE ID = :ID \n" +
                    "ORDER BY DTALTER DESC\n" +
                    "FETCH FIRST 1 ROW ONLY");

            sql.setNamedParameter("ID", numos);


            rset = sql.executeQuery();

            while (rset.next()) {
                historicoOs.setIdOS(rset.getBigDecimal("ID"));
                historicoOs.setCodUsu(rset.getBigDecimal("CODUSU"));
                historicoOs.setDescricao(rset.getString("DESCR"));
            }

        } catch (Exception e) {
            MGEModelException.throwMe(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return historicoOs;
    }
}
