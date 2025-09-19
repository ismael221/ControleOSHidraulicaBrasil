package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.ChaveAgrupamento;
import br.com.bolt.controle.os.model.Cotacao;
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
import java.util.LinkedHashMap;
import java.util.Map;

public class CotacaoRepository {

    public ArrayList<Cotacao> encontrarCotacoes(BigDecimal idOS) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        Map<ChaveAgrupamento, Cotacao> agrupamentoMap = new LinkedHashMap<>();

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT \n" +
                    "OS.*,\n" +
                    "MAT.*,\n" +
                    "PART.*\n" +
                    "FROM \n" +
                    "AD_MATERIAIS MAT\n" +
                    "LEFT JOIN AD_PARTNAME PART\n" +
                    "ON PART.CODPARTNAME=MAT.CODPARTNAME\n" +
                    "AND PART.ID=MAT.ID\n" +
                    "LEFT JOIN AD_CONTROLEOS OS\n" +
                    "ON OS.ID=MAT.ID\n" +
                    "WHERE OS.ID=:IDOS");

            sql.setNamedParameter("IDOS", idOS);


            rset = sql.executeQuery();

            while (rset.next()) {

                BigDecimal nunota = rset.getBigDecimal("NUNOTA");
                BigDecimal codProduto = rset.getBigDecimal("CODPROD");
                String nuos = rset.getString("NUOS");

                ChaveAgrupamento chave = new ChaveAgrupamento(nunota, codProduto);

                Cotacao existente = agrupamentoMap.get(chave);

                if (existente == null){
                    Cotacao novo = new Cotacao();
                    novo.setOrcamento(nunota);
                    novo.setQuantidade(rset.getBigDecimal("QUANTIDADE"));
                    novo.setDiameInterno(rset.getDouble("DIAINTER"));
                    novo.setDiameExterno(rset.getDouble("DIAEXTER"));
                    novo.setPartname(rset.getBigDecimal("PARTNAME"));

                    agrupamentoMap.put(chave,novo);
                }else{
                    // Já existe: concatena o número da OS
                    String atual = existente.getOss();
                    if (!atual.contains(nuos)) {
                        existente.setOss(atual + ", " + nuos);
                    }
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

        return new ArrayList<>(agrupamentoMap.values());
    }
}
