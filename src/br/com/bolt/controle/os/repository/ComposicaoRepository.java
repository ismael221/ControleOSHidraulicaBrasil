package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.Componente;
import br.com.bolt.controle.os.model.Partname;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import com.sankhya.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static br.com.sankhya.jape.core.JapeSession.close;

public class ComposicaoRepository {

    JapeSession.SessionHandle hnd = null;

    public void salvarComponente(Componente componente) {
        System.out.println("Salvando componente :  " + componente.toString());
        hnd = JapeSession.open();
        try {
            EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
            DynamicVO composicaoVO = (DynamicVO) dwfFacade.getDefaultValueObjectInstance("AD_COMPOSICAO");
            composicaoVO.setProperty("CODPROD", componente.getCodProd());
            composicaoVO.setProperty("CODPARTNUMBER", componente.getCodComponente());
            composicaoVO.setProperty("PARTNAME", componente.getPartname());
            composicaoVO.setProperty("QTD", componente.getQuantidade());
            dwfFacade.createEntity("AD_COMPOSICAO", (EntityVO) composicaoVO);
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            close(hnd);
        }

    }

    public List<Componente> listarComponentes(BigDecimal item) {
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

            sql.appendSql("SELECT * FROM AD_COMPOSICAO WHERE CODPROD = :CODPROD");

            sql.setNamedParameter("CODPROD", item);


            rset = sql.executeQuery();

            while (rset.next()) {
                Componente componente = new Componente();
                componente.setCodComponente(rset.getBigDecimal("CODPARTNUMBER"));
                componente.setPartname(rset.getBigDecimal("PARTNAME"));
                componente.setQuantidade(rset.getBigDecimal("QTD"));
                componente.setCodProd(rset.getBigDecimal("CODPROD"));
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
