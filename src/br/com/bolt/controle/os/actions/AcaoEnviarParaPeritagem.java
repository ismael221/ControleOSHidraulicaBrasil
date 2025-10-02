package br.com.bolt.controle.os.actions;

import br.com.bolt.controle.os.enums.StatusOS;
import br.com.bolt.controle.os.model.Partname;
import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.service.PartnameService;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AcaoEnviarParaPeritagem implements AcaoRotinaJava {
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        Registro[] linhas = contexto.getLinhas();
        ControleOsRepository controleOsRepository = new ControleOsRepository();
        PartnameService partnameService = new PartnameService();
        for (Registro linha : linhas) {
            BigDecimal codOs = (BigDecimal) linha.getCampo("ID");
            controleOsRepository.atualizarStatusOSByPK(codOs, StatusOS.AGUARDANDO_PERITAGEM.getCodigo());

            List<Partname> partnameList = new ArrayList();

            Partname partname95 = new Partname(new BigDecimal(209990001), new BigDecimal(95), BigDecimal.ONE,new BigDecimal(6));
            Partname partname96 = new Partname(new BigDecimal(209990002), new BigDecimal(96), BigDecimal.ONE,new BigDecimal(6));
            Partname partname97 = new Partname(new BigDecimal(209990003), new BigDecimal(97), BigDecimal.ONE,new BigDecimal(6));

            partnameList.add(partname95);
            partnameList.add(partname96);
            partnameList.add(partname97);

            partnameService.inserirPartnamesObrigatorios(partnameList, codOs);
        }
    }
}