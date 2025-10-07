package br.com.bolt.controle.os.repository;

import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.bmp.PersistentLocalEntity;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

import java.math.BigDecimal;

public class MaterialRepository {


    public void lancarMaterial(BigDecimal codOs, BigDecimal codPartname, BigDecimal codServico, String descricao) {
        System.out.println("Lançando material referente ao serviço de substituição...");
        try {
            EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
            DynamicVO materialVO = (DynamicVO) dwfFacade.getDefaultValueObjectInstance("AD_MATERIAIS");
            materialVO.setProperty("ID", codOs);
            materialVO.setProperty("CODPARTNAME", codPartname);
            materialVO.setProperty("CODSERV", codServico);
            materialVO.setProperty("DESCR", descricao);
            materialVO.setProperty("QUANTIDADE", BigDecimal.ONE);

            PersistentLocalEntity salvo = dwfFacade.createEntity("AD_MATERIAIS", (EntityVO) materialVO);
            DynamicVO salvoVO = (DynamicVO) salvo.getValueObject();
        } catch (Exception e) {
            Utils.logarErro(e);
        }
    }

}
