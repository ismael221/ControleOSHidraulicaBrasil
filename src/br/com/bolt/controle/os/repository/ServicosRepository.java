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

public class ServicosRepository {

    JapeSession.SessionHandle hnd = null;

    public void lancarServico(BigDecimal codOs, BigDecimal codPartname, BigDecimal codProd) {
        System.out.println("Lançando serviço de substituição...");
        try {
            hnd = JapeSession.open();
            JapeWrapper servicoDAO = JapeFactory.dao("AD_SERVICOS");
            DynamicVO save = servicoDAO.create()
                    .set("ID", codOs)
                    .set("CODPARTNAME", codPartname)
                    .set("CODSERV", encontrarPkServicos(codOs, codPartname))
                    .set("CODPROD", codProd)
                    .set("QTD", BigDecimal.ONE)
                    .save();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

    public BigDecimal encontrarPkServicos(BigDecimal codOs, BigDecimal codPartname) {
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

            sql.appendSql("SELECT NVL(MAX(CODSERV),0) + 1 AS PRIMARYKEY FROM AD_SERVICOS WHERE ID = :CODOS AND CODPARTNAME = :CODPARTNAME");

            sql.setNamedParameter("CODOS", codOs);
            sql.setNamedParameter("CODPARTNAME", codPartname);

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

    public BigDecimal consultarServicoRelacionadoPartname(BigDecimal partname) {

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        BigDecimal servico = BigDecimal.ZERO;
        try {
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();
            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT CODPROD FROM TGFPRO WHERE CODGRUPOPROD = :PARTNAME");
            sql.setNamedParameter("PARTNAME", partname);

            rset = sql.executeQuery();

            if (rset.next()) {
                servico = rset.getBigDecimal("CODPROD");
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
        }

        return servico;
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
