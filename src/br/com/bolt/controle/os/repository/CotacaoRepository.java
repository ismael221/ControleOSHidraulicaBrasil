package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import com.sankhya.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CotacaoRepository {

    public List<BigDecimal> encontrarCotacoes() {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        List<BigDecimal> listaOsPorNuOS = new ArrayList<>();

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT * FROM AD_CONTROLEOS WHERE STATUS = :STATUS");

            sql.setNamedParameter("STATUS", new BigDecimal("4"));


            rset = sql.executeQuery();

            while (rset.next()) {
                BigDecimal codOs = rset.getBigDecimal("ID");
                listaOsPorNuOS.add(codOs);
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return listaOsPorNuOS;

    }
}
