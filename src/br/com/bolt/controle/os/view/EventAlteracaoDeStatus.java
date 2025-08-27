package br.com.bolt.controle.os.view;

import br.com.bolt.controle.os.service.HistoricoOsService;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.ws.ServiceContext;

import java.math.BigDecimal;
import java.util.Objects;

public class EventAlteracaoDeStatus implements EventoProgramavelJava {

    @Override
    public void beforeInsert(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeUpdate(PersistenceEvent event) throws Exception {
        ServiceContext ctx = ServiceContext.getCurrent();
        Object auth = ctx.getAutentication();
        AuthenticationInfo authInfo = (AuthenticationInfo) auth;

        DynamicVO controleOsOldVo = (DynamicVO) event.getOldVO();
        DynamicVO controleOsNewdVo = (DynamicVO) event.getVo();
        BigDecimal numos = controleOsNewdVo.asBigDecimal("ID");
        HistoricoOsService historicoOsService = new HistoricoOsService();

        BigDecimal oldStatus = controleOsOldVo.asBigDecimal("STATUS");
        BigDecimal newStatus = controleOsNewdVo.asBigDecimal("STATUS");

        if (!Objects.equals(oldStatus, newStatus)) {

            String descOldStatus = historicoOsService.consultarDescricaoStatus(oldStatus);
            String descNewStatus = historicoOsService.consultarDescricaoStatus(newStatus);

            historicoOsService.salvarRegistro(descNewStatus, descOldStatus, numos, authInfo.getUserID());

            System.out.println("Uusário logado no beforeUpdate: " + authInfo.getName() + " " + authInfo.getUserID());
            System.out.println("Status alterado de " + controleOsOldVo.asBigDecimal("STATUS") + " para -> " + controleOsNewdVo.asBigDecimal("STATUS"));
        }

    }

    @Override
    public void beforeDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterUpdate(PersistenceEvent event) throws Exception {
    }

    @Override
    public void afterDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext tranCtx) throws Exception {

    }
}
