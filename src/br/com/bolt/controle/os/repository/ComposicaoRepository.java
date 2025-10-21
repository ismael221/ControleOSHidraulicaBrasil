package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.model.Componente;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

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
            composicaoVO.setProperty("CODPARTNAME", componente.getCodComponente());
            composicaoVO.setProperty("QTD", componente.getQuantidade());
            dwfFacade.createEntity("AD_COMPOSICAO", (EntityVO) composicaoVO);
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            close(hnd);
        }

    }
}
