package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import com.sankhya.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;

public class MaterialRepository {

    JapeSession.SessionHandle hnd = null;

    public void lancarMaterial(BigDecimal codOs, BigDecimal codPartname, BigDecimal codServico, String descricao) {
        System.out.println("Lançando material referente ao serviço de substituição...");
        try {
            hnd = JapeSession.open();
            JapeWrapper servicoDAO = JapeFactory.dao("AD_SERVICOS");
            DynamicVO save = servicoDAO.create()
                    .set("ID", codOs)
                    .set("SEQMAT", encontrarPkMateriais(codOs))
                    .set("CODPARTNAME", codPartname)
                    .set("CODSERV", codServico)
                    .set("DESCR", descricao)
                    .set("QUANTIDADE", BigDecimal.ONE)
                    .save();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

    public BigDecimal encontrarPkMateriais(BigDecimal codOs) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        BigDecimal pkServico = BigDecimal.ZERO;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT NVL(MAX(SEQMAT),0) + 1 AS PRIMARYKEY FROM AD_MATERIAIS WHERE ID = :CODOS");

            sql.setNamedParameter("CODOS", codOs);

            rset = sql.executeQuery();

            if (rset.next()) {
                pkServico = rset.getBigDecimal("PRIMARYKEY");
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }
        return pkServico;

    }
}
