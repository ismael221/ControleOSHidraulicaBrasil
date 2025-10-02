package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.Componente;
import br.com.bolt.controle.os.model.Partname;
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

public class PartnameRepository {

    JapeSession.SessionHandle hnd = null;

    public void inserirPartname(Partname partname, BigDecimal codOs) {
        System.out.println("Inserindo partname " + partname + "na OS " + codOs);

        try {
            hnd = JapeSession.open();
            JapeWrapper partnameDAO = JapeFactory.dao("AD_PARTNAME");
            DynamicVO save = partnameDAO.create()
                    .set("ID", codOs)
                    .set("ORDEM", partname.getOrdem())
                    .set("DECISAO", partname.getDecisao())
                    .set("QTD", partname.getQuantidade())
                    .set("PARTNAME", partname.getPartname())
                    .save();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }


    public List<Partname> encontrarPartnames(BigDecimal macrogrupo) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        List<Partname> partnames = new ArrayList<>();

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT CODGRUPOPROD,ORDEM FROM AD_MACROGRUPO WHERE CODMAC = :CODMAC");

            sql.setNamedParameter("CODMAC", macrogrupo);


            rset = sql.executeQuery();

            while (rset.next()) {
                Partname partname = new Partname();
                partname.setPartname(rset.getBigDecimal("CODGRUPOPROD"));
                partname.setOrdem(rset.getBigDecimal("ORDEM"));
                partname.setQuantidade(BigDecimal.ONE);
                partnames.add(partname);
            }


        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return partnames;
    }

    public List<Componente> gerarComponentesDoPartname(BigDecimal codOS) {
        System.out.println("Gerando componentes do partname " + codOS);
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        List<Componente> componentes = new ArrayList<>();

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT CODPROD,QTD FROM AD_PARTNAME WHERE ID = :CODOS");

            sql.setNamedParameter("CODOS", codOS);


            rset = sql.executeQuery();

            while (rset.next()) {
                Componente componente = new Componente();
                componente.setCodComponente(rset.getBigDecimal("CODPROD"));
                componente.setQuantidade(Double.valueOf(String.valueOf(rset.getBigDecimal("QTD"))));
                componente.setUnidade("UN");
                componentes.add(componente);
            }


        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return componentes;
    }
}
