package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import com.sankhya.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;

public class PrecoRepository {

    public Double custoTotalServico(BigDecimal codOs, BigDecimal codPartname) {

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        Double custoTotalServico = 0.0;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT\n" +
                    " NVL( SUM  (\n" +
                    "        -- CUSTO TOTAL HORAS\n" +
                    "        ((S.QTD * S.TEMPO) * \n" +
                    "            (SELECT SNK_GET_PRECO(6, S.CODPROD, SYSDATE) FROM DUAL)\n" +
                    "        )\n" +
                    "        +\n" +
                    "        -- CUSTO TOTAL ÁREA\n" +
                    "        (\n" +
                    "            (((ACOS(-1) * S.DIAMETRO * S.COMPRIMENTO) / 1000) * S.QTD) *\n" +
                    "            (SELECT SNK_GET_PRECO(6, S.CODPROD, SYSDATE) FROM DUAL)\n" +
                    "        )\n" +
                    "    ),0) AS CUSTO_TOTAL\n" +
                    "FROM AD_SERVICOS S\n" +
                    "WHERE  S.ID = :CODOS\n" +
                    "  AND S.CODPARTNAME = :CODPARTNAME");

            sql.setNamedParameter("CODOS", codOs);
            sql.setNamedParameter("CODPARTNAME", codPartname);

            rset = sql.executeQuery();

            if (rset.next()) {
                custoTotalServico = rset.getDouble(1);
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return custoTotalServico;
    }

    public Double markupServico(BigDecimal codOs) {

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        Double markup = 0.0;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT CP.MARKUP \n" +
                    "FROM AD_COMPLEXOS CP\n" +
                    "LEFT JOIN AD_CONTROLEOS OS\n" +
                    "ON OS.GRAUCOMPLE=CP.GRAU\n" +
                    "WHERE OS.ID = :CODOS");

            sql.setNamedParameter("CODOS", codOs);

            rset = sql.executeQuery();

            if (rset.next()) {
                markup = rset.getDouble(1);
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return markup;
    }

    public Double totalVendaMaterial(BigDecimal codOs, BigDecimal codPartname) {

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        Double totalVendaMaterial = 0.0;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT SUM(NVL((M.VLRVENDAUNT *  M.QUANTIDADE),0)) FROM AD_MATERIAIS M WHERE M.ID = :CODOS AND M.CODPARTNAME = :CODPARTNAME");

            sql.setNamedParameter("CODPARTNAME", codPartname);
            sql.setNamedParameter("CODOS", codOs);

            rset = sql.executeQuery();

            if (rset.next()) {
                totalVendaMaterial = rset.getDouble(1);
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return totalVendaMaterial;
    }

    public Double taxa() {

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        Double taxa = 0.0;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT NUMDEC FROM TSIPAR WHERE CHAVE = 'PERCACRESCOS'");

            rset = sql.executeQuery();

            if (rset.next()) {
                taxa = rset.getDouble(1);
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return taxa;
    }

}
