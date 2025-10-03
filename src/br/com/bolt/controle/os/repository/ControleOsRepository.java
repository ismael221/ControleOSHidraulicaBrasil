package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.OrdemDeServico;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.bmp.PersistentLocalEntity;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
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

    JapeSession.SessionHandle hnd = null;

    public void atualizandoNumOsByPK(BigDecimal codOS, String numOs) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("UPDATE AD_CONTROLEOS SET NUOS = :NUOS WHERE ID=:CODOS");

            sql.setNamedParameter("NUOS", numOs);
            sql.setNamedParameter("CODOS", codOS);

            sql.executeUpdate();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }
    }

    public void atualizarStatusOSByPK(BigDecimal codOS, BigDecimal status) {
        System.out.println("Atualizando status da OS : " + codOS + " - " + status);
        try {
            hnd = JapeSession.open();
            JapeFactory.dao("AD_CONTROLEOS").
                    prepareToUpdateByPK(codOS)
                    .set("STATUS", status)
                    .update();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

    public void aprovarOS(BigDecimal codOs) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("UPDATE AD_CONTROLEOS SET APROVADA = :APROVADA WHERE ID=:CODOS");

            sql.setNamedParameter("APROVADA", "S");
            sql.setNamedParameter("CODOS", codOs);

            sql.executeUpdate();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }
    }

    public void atualizarStatusEreprovarOs(BigDecimal codOS, BigDecimal status) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("UPDATE AD_CONTROLEOS SET APROVADA = :APROVADA, STATUS = :STATUS WHERE ID=:CODOS");

            sql.setNamedParameter("APROVADA", "N");
            sql.setNamedParameter("STATUS", status);
            sql.setNamedParameter("CODOS", codOS);

            sql.executeUpdate();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }
    }

    public List<BigDecimal> encontrarOsPorNuOS(String nuOs) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
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
        try {
            EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

            DynamicVO osVO = (DynamicVO) dwfFacade.getDefaultValueObjectInstance("AD_CONTROLEOS");
            osVO.setProperty("REVISAO", ordemDeServico.getRevisao() == null
                    ? BigDecimal.ONE
                    : ordemDeServico.getRevisao().add(BigDecimal.ONE));
            osVO.setProperty("NUOS", ordemDeServico.getNuOS());
            osVO.setProperty("DESCR", ordemDeServico.getDescricaoProblema());
            osVO.setProperty("DESCRNOTA", ordemDeServico.getDescricaoParaNota());
            osVO.setProperty("CODEMP", ordemDeServico.getEmpresa());
            osVO.setProperty("CODEQUIP", ordemDeServico.getEquipamento());
            osVO.setProperty("CODMACROGRP", ordemDeServico.getMacrogrupo());
            osVO.setProperty("NUNOTA", ordemDeServico.getOrcamento());
            osVO.setProperty("CODPARC", ordemDeServico.getParceiro());
            osVO.setProperty("OFICINA", ordemDeServico.getOficina());
            osVO.setProperty("STATUS", ordemDeServico.getStatusOS());

            PersistentLocalEntity salvo = dwfFacade.createEntity("AD_CONTROLEOS", (EntityVO) osVO);
            DynamicVO salvoVO = (DynamicVO) salvo.getValueObject();
        } catch (Exception e) {
            Utils.logarErro(e);
        }
    }


    public void atualizarMotivoNaoAprovacao(BigDecimal codOS, String motivo, BigDecimal status) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("UPDATE AD_CONTROLEOS SET MOTIVONAPROVA = :MOTIVONAPROVA, STATUS = :STATUS WHERE ID=:CODOS");

            sql.setNamedParameter("MOTIVONAPROVA", motivo.toCharArray());
            sql.setNamedParameter("STATUS", status);
            sql.setNamedParameter("CODOS", codOS);

            sql.executeUpdate();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }
    }
}
