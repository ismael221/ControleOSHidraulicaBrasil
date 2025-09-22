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

public class ServicosRepository {

    private final BigDecimal substituicao = new BigDecimal(24);

    public void lancarServico(BigDecimal codOs, BigDecimal codPartname) {
        System.out.println("Lançando serviço de substituição...");

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

            sql.appendSql("INSERT INTO AD_SERVICOS (ID, CODPARTNAME, CODPROD, QTD) VALUES (:ID, :CODPARTNAME, :CODPROD, :QTD)");
            sql.setNamedParameter("ID", codOs);
            sql.setNamedParameter("CODPARTNAME", codPartname);
            sql.setNamedParameter("CODPROD", substituicao);
            sql.setNamedParameter("QTD", BigDecimal.ONE);


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

    public BigDecimal quantidadeFinalizados(BigDecimal idOs) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        BigDecimal finalizados = BigDecimal.ZERO;
        try {
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();
            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT COUNT(*) AS TOTAL FROM AD_SERVICOS WHERE (STATUS <> 'F'  OR STATUS IS NULL) AND ID = :ID");
            sql.setNamedParameter("ID", idOs);

            rset = sql.executeQuery();

            if (rset.next()) {
                finalizados = rset.getBigDecimal("TOTAL");
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
        }

        return finalizados;
    }

    public BigDecimal quantidadeDeServicos(BigDecimal idOs) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        BigDecimal servicos = BigDecimal.ZERO;

        try {
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();
            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT COUNT(*) FROM AD_SERVICOS WHERE ID = :ID");
            sql.setNamedParameter("ID", idOs);

            rset = sql.executeQuery();

            if (rset.next()) {
                servicos = rset.getBigDecimal("TOTAL");
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
        }

        return servicos;
    }

}
