package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.AgrupadoDTO;
import br.com.bolt.controle.os.model.MaterialCotacaoDTO;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
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

public class CotacaoRepository {

    public List<MaterialCotacaoDTO> buscarItensParaCotacao(BigDecimal nunota) {

        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        SessionHandle hnd = null;
        List<MaterialCotacaoDTO> lista = new ArrayList<>();

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT \n" +
                    "C.NUOS,\n" +
                    "M.ID,\n" +
                    "M.CODPARTNAME,\n" +
                    "M.CODSERV,\n" +
                    "M.SEQMAT,\n" +
                    "M.QUANTIDADE,\n" +
                    "M.COMPRIMENTO,\n" +
                    "M.CODPRODUTO,\n" +
                    "M.UNIDADE,\n" +
                    "M.CONTROLE,\n" +
                    "M.DESCR,\n" +
                    "M.DIAINTER,\n" +
                    "M.DIAEXTER\n" +
                    "FROM AD_MATERIAIS M\n" +
                    "JOIN AD_CONTROLEOS C\n" +
                    "  ON M.ID = C.ID\n" +
                    "WHERE C.NUNOTA = :NUNOTA\n");

            sql.setNamedParameter("NUNOTA", nunota);

            rset = sql.executeQuery();

            while (rset.next()) {
                MaterialCotacaoDTO dto = new MaterialCotacaoDTO();
                dto.setNuos(rset.getString("NUOS"));
                dto.setDescr(rset.getString("DESCR"));
                dto.setQuantidade(rset.getBigDecimal("QUANTIDADE"));
                dto.setCodProd(rset.getBigDecimal("CODPRODUTO"));
                dto.setCodPartname(rset.getBigDecimal("CODPARTNAME"));
                lista.add(dto);
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return lista;
    }

    public void salvarMaterialCotacao(AgrupadoDTO dto, BigDecimal codcotacao) {
        try {
            EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
            DynamicVO materialCotacaoVO = (DynamicVO) dwfFacade.getDefaultValueObjectInstance("AD_MATERIALCOTACAO");
            materialCotacaoVO.setProperty("CODCOT", codcotacao);
            materialCotacaoVO.setProperty("OSS", String.join(",", dto.getNuos()).toCharArray());
            materialCotacaoVO.setProperty("QTD", dto.getQuantidade());
            materialCotacaoVO.setProperty("PARTNAME", BigDecimal.ZERO);
            dwfFacade.createEntity("AD_MATERIALCOTACAO", (EntityVO) materialCotacaoVO);
        } catch (Exception e) {
            Utils.logarErro(e);
        }
    }
}
