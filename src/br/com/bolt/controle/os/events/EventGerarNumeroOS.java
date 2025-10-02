package br.com.bolt.controle.os.events;

import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.repository.OficinaRepository;
import br.com.bolt.controle.os.service.OSNumeroGeneratorService;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;

public class EventGerarNumeroOS implements EventoProgramavelJava {

    @Override
    public void beforeInsert(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeUpdate(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent event) throws Exception {
        System.out.println("EventGerarNumeroOS::AfterInsert");
        try {
            OficinaRepository oficinaRepository = new OficinaRepository();
            ControleOsRepository controleOsRepository = new ControleOsRepository();


            DynamicVO controleOSVo = (DynamicVO) event.getVo();
            BigDecimal codOficina = controleOSVo.asBigDecimal("OFICINA");
            BigDecimal idOS = controleOSVo.asBigDecimal("ID");
            String numeroAtual = controleOSVo.asString("NUOS");

            if (numeroAtual == null || numeroAtual.equals("")) {
                String oficina = oficinaRepository.consultarPrefixoOficina(codOficina);
                String numOs = OSNumeroGeneratorService.gerarNumero(oficina);

                controleOsRepository.atualizandoNumOsByPK(idOS, numOs);
            }else {
                System.out.println("OS Já possui numeração cadastrada");
            }

        } catch (Exception e) {
            System.out.println("Erro ao gerar número da OS");
            Utils.logarErro(e);
        }

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
