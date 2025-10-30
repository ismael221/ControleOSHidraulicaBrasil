package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.Item;
import br.com.bolt.controle.os.model.ItemNota;
import br.com.bolt.controle.os.model.OrdemDeServico;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import com.sankhya.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;

public class ItemsRepository {

    public List<Item> listaItemsNotaFiscal(BigDecimal codOs) {

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        List<Item> items = null;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT CODPROD,QTD FROM AD_PARTNAME WHERE ID = :CODOS");

            sql.setNamedParameter("CODOS", codOs);

            rset = sql.executeQuery();

            while (rset.next()) {
                ItemNota item = new ItemNota();
                item.setCodProd(rset.getBigDecimal("CODPROD"));
                item.setQtdNeg(Double.valueOf(String.valueOf(rset.getBigDecimal("QTD"))));
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }
        return items;
    }

    public Double precoServicosDoPartname(BigDecimal codOs) {

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        List<Item> items = null;
        Double precoServicosDoPartname = 0.0;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT NVL(SUM(VLRTOTVENDA),0) FROM AD_PARTNAME WHERE TIPNOTA = 1 AND ID = :CODOS");

            sql.setNamedParameter("CODOS", codOs);

            rset = sql.executeQuery();

            if (rset.next()) {
                precoServicosDoPartname = rset.getDouble(1);
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }
        return precoServicosDoPartname;
    }
}
