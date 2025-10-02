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

    private final HistoricoOsRepository historicoOsRepository = new HistoricoOsRepository();

    public void salvarRegistro(String novoStatus, String statusOld, BigDecimal numos, BigDecimal codUsu) throws MGEModelException {
        HistoricoOs historicoOs = historicoOsRepository.consultarHistorico(numos);

        if (historicoOs == null) {
            historicoOs = new HistoricoOs();
        }

        historicoOs.setDtAlter(Timestamp.from(Instant.now()));
        historicoOs.setCodUsu(codUsu);
        historicoOs.setIdOS(numos);

        if (isNullOrEmpty(statusOld) || isNullOrEmpty(novoStatus)) {
            historicoOs.setDescricao("OS Gerada, status inicial: Entendendo demanda");
        } else {
            historicoOs.setDescricao("De " + statusOld + " -> Para " + novoStatus);
        }

        historicoOsRepository.salvarHistorico(historicoOs);
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public String consultarDescricaoStatus(BigDecimal numos) throws MGEModelException {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        JapeSession.SessionHandle hnd = null;
        String descricao = "";

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
