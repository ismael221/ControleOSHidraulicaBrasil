package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.OrdemDeServico;
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
import java.util.ArrayList;
import java.util.List;


public class OrdemDeServicoRepository {

    public List<OrdemDeServico> gerarOrdensDeServico(BigDecimal nunota) {

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        List<OrdemDeServico> ordemDeServicos = new ArrayList<>();

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT * FROM TGFITE WHERE NUNOTA = :NUNOTA");

            sql.setNamedParameter("NUNOTA", nunota);
            System.out.println(sql);
            rset = sql.executeQuery();

            while (rset.next()) {
                BigDecimal quantidade = rset.getBigDecimal("QTDNEG");

                while (quantidade.compareTo(BigDecimal.ZERO) > 0) {
                    OrdemDeServico ordemDeServico = new OrdemDeServico();
                    ordemDeServico.setEmpresa(rset.getBigDecimal("CODEMP"));
                    ordemDeServico.setMacrogrupo(rset.getBigDecimal("AD_MACROGRUPO"));
                    ordemDeServico.setRevisao(BigDecimal.ZERO);
                    ordemDeServico.setOficina(rset.getBigDecimal("AD_OFICINAITEM"));
                    ordemDeServico.setOrcamento(rset.getBigDecimal("NUNOTA"));
                    // ordemDeServico.setParceiro(rset.getBigDecimal("CODPARC"));
                    ordemDeServico.setStatusOS(BigDecimal.ONE);
                    ordemDeServicos.add(ordemDeServico);
                    quantidade = quantidade.subtract(BigDecimal.ONE);
                }
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }
        return ordemDeServicos;
    }

    public void salvarOrdensDeServico(OrdemDeServico ordemDeServico) {
        System.out.println("Salvando ordemDeServico: " + ordemDeServico.toString());
        JapeSession.SessionHandle hnd = null;
        try {
            hnd = JapeSession.open();
            JapeWrapper empresaDAO = JapeFactory.dao("AD_CONTROLEOS");
            DynamicVO save = empresaDAO.create()
                    .set("OFICINA", ordemDeServico.getOficina())
                    .set("CODEMP", ordemDeServico.getEmpresa())
                    .set("CODPARC", ordemDeServico.getParceiro())
                    .set("STATUS", ordemDeServico.getStatusOS())
                    .set("CODMACROGRP", ordemDeServico.getMacrogrupo())
                    .set("NUNOTA", ordemDeServico.getOrcamento())
                    .set("REVISAO", BigDecimal.ONE)
                    .save();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }
}
