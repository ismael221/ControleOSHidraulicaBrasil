package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.ChaveAgrupamento;
import br.com.bolt.controle.os.model.Cotacao;
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
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CotacaoRepository {

    JapeSession.SessionHandle hnd = null;

    public ArrayList<Cotacao> encontrarCotacoes(BigDecimal idOS) {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
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

                if (existente == null) {
                    Cotacao novo = new Cotacao();
                    novo.setOrcamento(nunota);
                    novo.setQuantidade(rset.getBigDecimal("QUANTIDADE"));
                    novo.setDiameInterno(new BigDecimal(rset.getDouble("DIAINTER")));
                    novo.setDiameExterno(rset.getDouble("DIAEXTER"));
                    novo.setPartname(rset.getBigDecimal("PARTNAME"));
                    novo.setOrcamento(rset.getBigDecimal("NUNOTA"));
                    novo.setDescricao(rset.getString("DESCR"));
                    novo.setNumeroOS(rset.getBigDecimal("ID"));
                    novo.setOss(rset.getString("NUOS"));
                    agrupamentoMap.put(chave, novo);
                } else {
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

    public void salvarCotacao(Cotacao cotacao) {
        System.out.println("Salvando cotação ...");
        try {
            hnd = JapeSession.open();
            JapeWrapper cotacaoDAO = JapeFactory.dao("AD_COTACAOOS");
            DynamicVO save = cotacaoDAO.create()
                    .set("CODPROD", cotacao.getCodProduto())
                    .set("DESCR", cotacao.getDescricao().toCharArray())
                    .set("QTD", cotacao.getQuantidade())
                    .set("NUNOTA", cotacao.getOrcamento())
                    .set("DIAMEINTER", cotacao.getDiameInterno())
                    .set("CODOS", cotacao.getNumeroOS())
                    .set("PARTNAME", cotacao.getPartname())
                    .save();

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }

    }

    public BigDecimal encontrarPkCotacao() {
        JdbcWrapper jdbc = null;
        BigDecimal codCotacao = BigDecimal.ZERO;

        try {
            hnd = JapeSession.open();
            EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

            jdbc = dwfFacade.getJdbcWrapper();
            jdbc.openSession();

            CallableStatement cstmt = jdbc.getConnection().prepareCall("{call STP_KEYGEN_TGFNUM(?,?,?,?,?,?)}");
            cstmt.setQueryTimeout(60);

            cstmt.setString(1, "AD_COTACAOOS");
            cstmt.setBigDecimal(2, BigDecimal.ONE);
            cstmt.setString(3, "AD_COTACAOOS");
            cstmt.setString(4, "CODCOT");
            cstmt.setBigDecimal(5, BigDecimal.ZERO);

            cstmt.registerOutParameter(6, Types.NUMERIC);

            cstmt.execute();

            codCotacao = (BigDecimal) cstmt.getObject(6);

            System.out.println("Saida: " + codCotacao);
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);
        }

        return codCotacao;
    }
}
