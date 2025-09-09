package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import com.sankhya.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ControleOsRepository {

    public void atualizandoNumOsByPK(BigDecimal codOS, String numOs) {
        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();

            JapeFactory.dao("AD_CONTROLEOS").
                    prepareToUpdateByPK(codOS)
                    .set("NUOS", numOs)
                    .update();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

    public void atualizarStatusOSByPK(BigDecimal codOS, BigDecimal status) {
        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();

            JapeFactory.dao("AD_CONTROLEOS")
                    .prepareToUpdateByPK(codOS)
                    .set("STATUS", status)
                    .update();
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

    public void aprovarOS(BigDecimal codOs) {
        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();

            JapeFactory.dao("AD_CONTROLEOS")
                    .prepareToUpdateByPK(codOs)
                    .set("APROVADA", "S")
                    .update();
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

    public void atualizarStatusEreprovarOs(BigDecimal codOS, BigDecimal status) {
        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();

            JapeFactory.dao("AD_CONTROLEOS")
                    .prepareToUpdateByPK(codOS)
                    .set("APROVADA", "N")
                    .set("STATUS", status)
                    .update();
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

    public List<BigDecimal> encontrarOsPorNuOS(String nuOs) {
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

            sql.appendSql("SELECT ID FROM AD_CONTROLEOS WHERE NUOS = :NUOS");

            sql.setNamedParameter("NUOS", nuOs);


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
