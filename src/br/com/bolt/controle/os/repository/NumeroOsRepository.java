package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import com.sankhya.util.JdbcUtils;

import java.sql.ResultSet;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class NumeroOsRepository {

    public Map<Integer, Integer> consultarUltimoNumero() {
        System.out.println("Consultando Ultimo Número");

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        int anoAtual = Year.now().getValue();
        Map<Integer, Integer> sequencias = new HashMap<>();

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT DISTINCT NUOS FROM AD_CONTROLEOS WHERE NUOS IS NOT NULL ORDER BY NUOS DESC");

            rset = sql.executeQuery();

            int maiorNumero = 0;
            while (rset.next()) {
                String valor = rset.getString("NUOS");
                if (valor != null && valor.length() >= 5) {
                    String numeros = valor.substring(5); // pega só os 6 dígitos
                    int numero = Integer.parseInt(numeros);
                    if (numero > maiorNumero) {
                        maiorNumero = numero;
                    }
                }
            }
            sequencias.put(anoAtual, maiorNumero);

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return sequencias;
    }
}
