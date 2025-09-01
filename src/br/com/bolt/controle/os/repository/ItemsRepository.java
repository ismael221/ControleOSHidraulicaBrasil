package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.Item;
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

    public List<Item> listaItems(BigDecimal nunota) {

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

            sql.appendSql("SELECT * FROM TGFITE WHERE NUNOTA = :NUNOTA");

            sql.setNamedParameter("NUNOTA", nunota);

            rset = sql.executeQuery();

            while (rset.next()) {
                Item item = new Item();
                item.setNunota(rset.getBigDecimal("NUNOTA"));
                item.setMacroGrupo(rset.getBigDecimal("MACROGRUPO"));
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

}
