package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.HistoricoOs;
import br.com.bolt.controle.os.repository.HistoricoOsRepository;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import com.sankhya.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;

public class HistoricoOsService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HistoricoOsService.class);

    private final HistoricoOsRepository historicoOsRepository = new HistoricoOsRepository();

    public void salvarRegistro(String novoStatus, String statusOld, BigDecimal numos, BigDecimal codUsu) throws MGEModelException {
        logger.info("salvarRegistro: numos={}, statusOld='{}', novoStatus='{}'", numos, statusOld, novoStatus);

        try {
            HistoricoOs historicoOs = historicoOsRepository.consultarHistorico(numos);
            boolean isNew = (historicoOs == null);
            if (isNew) {
                historicoOs = new HistoricoOs();
            }

            historicoOs.setDtAlter(new java.sql.Timestamp(System.currentTimeMillis()));
            historicoOs.setCodUsu(codUsu);
            historicoOs.setIdOS(numos);

            String descricao;
            if (isNullOrEmpty(statusOld)) {
                // se não existir statusOld considero criação (você pode ajustar esta regra)
                descricao = "OS Gerada, status inicial Entendendo demanda";
            } else if (isNullOrEmpty(novoStatus)) {
                descricao = "Alteração: de " + statusOld.trim() + " (novo status vazio)";
            } else {
                descricao = "De " + statusOld.trim() + " -> Para " + novoStatus.trim();
            }
            historicoOs.setDescricao(descricao);

            historicoOsRepository.salvarHistorico(historicoOs);

            // Verificação imediata para ajudar no debug
            HistoricoOs salvo = historicoOsRepository.consultarHistorico(numos);
            if (salvo == null) {
                logger.error("Após chamar salvarHistorico, consultarHistorico retornou null — verificar salvarHistorico/transações.");
            } else {
                logger.info("Historico salvo com sucesso. descricao='{}'", salvo.getDescricao());
            }
        } catch (Exception e) {
            logger.error("Erro ao salvar historico", e);
            if (e instanceof MGEModelException) throw (MGEModelException) e;
            throw new MGEModelException(e);
        }
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public String consultarDescricaoStatus(BigDecimal numos) throws MGEModelException {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        String descricao = null;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);

            sql.appendSql("SELECT DESCR FROM AD_STATUSOS WHERE ID = :ID");

            sql.setNamedParameter("ID", numos);

            rset = sql.executeQuery();

            if (rset.next()) {
                descricao = rset.getString("DESCR");
            }

        } catch (Exception e) {
            MGEModelException.throwMe(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);

        }

        return descricao;
    }
}
