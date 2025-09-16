package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.Componente;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import com.sankhya.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComponentesRepository {

    public void salvarComponente(Componente componente) {
        System.out.println("Salvando componente :  " + componente.toString());
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

            sql.appendSql("INSERT INTO TGFICP (CODPROD,VARIACAO,CODLOCAL,CODETAPA,CODMATPRIMA,QTDMISTURA,CODVOL) VALUES (:CODPROD,:VARIACAO,:CODLOCAL,:CODETAPA,:CODMATPRIMA,:QTDMISTURA,:CODVOL)");

            sql.setNamedParameter("CODPROD", componente.getCodProd());
            sql.setNamedParameter("VARIACAO", new BigDecimal(3000));
            sql.setNamedParameter("CODLOCAL", BigDecimal.ZERO);
            sql.setNamedParameter("CODETAPA", BigDecimal.ZERO);
            sql.setNamedParameter("CODMATPRIMA", componente.getCodComponente());
            sql.setNamedParameter("QTDMISTURA", componente.getQuantidade());
            sql.setNamedParameter("CODVOL", "UN");

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

    public List<Componente> listarComponente(BigDecimal codProd) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        List<Componente> componentes = new ArrayList<Componente>();
        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT CODPROD,CODMATPRIMA,QTDMISTURA FROM TGFICP WHERE CODPROD = :CODPROD");

            sql.setNamedParameter("CODPROD", codProd);

            rset = sql.executeQuery();

            while (rset.next()) {
                Componente componente = new Componente();
                componente.setCodProd(rset.getBigDecimal("CODPROD"));
                componente.setCodComponente(rset.getBigDecimal("CODMATPRIMA"));
                componente.setQuantidade(rset.getDouble("QTDMISTURA"));
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
