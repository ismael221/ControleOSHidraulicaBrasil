package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.OrdemDeServico;
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
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
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

    public void criarNovaOs(OrdemDeServico ordemDeServico) {
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

            sql.appendSql("INSERT INTO AD_CONTROLEOS " +
                    "(ID, REVISAO, NUOS, DESCR, DESCRNOTA, CODEMP, CODEQUIP, CODMACROGRP, NUNOTA, CODPARC, OFICINA, STATUS) " +
                    "VALUES (:ID, :REVISAO, :NUOS, :DESCR, :DESCRNOTA, :CODEMP, :CODEQUIP, :CODMACROGRP, :NUNOTA, :CODPARC, :OFICINA, :STATUS)");


            sql.setNamedParameter("ID", encontrarPkOs());
            sql.setNamedParameter("REVISAO", ordemDeServico.getRevisao() == null
                    ? BigDecimal.ONE
                    : ordemDeServico.getRevisao().add(BigDecimal.ONE));
            sql.setNamedParameter("NUOS", ordemDeServico.getNuOS());
            sql.setNamedParameter("DESCR", ordemDeServico.getDescricaoProblema());
            sql.setNamedParameter("DESCRNOTA", ordemDeServico.getDescricaoParaNota());
            sql.setNamedParameter("CODEMP", ordemDeServico.getEmpresa());
            sql.setNamedParameter("CODEQUIP", ordemDeServico.getEquipamento());
            sql.setNamedParameter("CODMACROGRP", ordemDeServico.getMacrogrupo());
            sql.setNamedParameter("NUNOTA", ordemDeServico.getOrcamento());
            sql.setNamedParameter("CODPARC", ordemDeServico.getParceiro());
            sql.setNamedParameter("OFICINA", ordemDeServico.getOficina());
            sql.setNamedParameter("STATUS", ordemDeServico.getStatusOS());


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

    public BigDecimal encontrarPkOs() {
        JapeSession.SessionHandle hnd = null;
        JdbcWrapper jdbc = null;
        BigDecimal nuos = BigDecimal.ZERO;

        try {
            hnd = JapeSession.open();
            EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

            jdbc = dwfFacade.getJdbcWrapper();
            jdbc.openSession();

            CallableStatement cstmt = jdbc.getConnection().prepareCall("{call STP_KEYGEN_TGFNUM(?,?,?,?,?,?)}");
            cstmt.setQueryTimeout(60);

            cstmt.setString(1, "AD_CONTROLEOS");
            cstmt.setBigDecimal(2, BigDecimal.ONE);
            cstmt.setString(3, "AD_CONTROLEOS");
            cstmt.setString(4, "ID");
            cstmt.setBigDecimal(5, BigDecimal.ZERO);

            cstmt.registerOutParameter(6, Types.NUMERIC);

            cstmt.execute();

            nuos = (BigDecimal) cstmt.getObject(6);

            System.out.println("Saida: " + nuos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);
        }

        return nuos;
    }
}
