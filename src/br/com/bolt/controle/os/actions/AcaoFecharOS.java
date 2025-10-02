package br.com.bolt.controle.os.actions;

import br.com.bolt.controle.os.enums.StatusOS;
import br.com.bolt.controle.os.model.Componente;
import br.com.bolt.controle.os.repository.ComponentesRepository;
import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.repository.PartnameRepository;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;
import java.util.List;

public class AcaoFecharOS implements AcaoRotinaJava {
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        Registro[] linhas = contexto.getLinhas();
        ControleOsRepository controleOsRepository = new ControleOsRepository();
        PartnameRepository partnameRepository = new PartnameRepository();
        ComponentesRepository componentesRepository = new ComponentesRepository();

        for (Registro linha : linhas) {
            BigDecimal codOs = (BigDecimal) linha.getCampo("ID");
            BigDecimal codProd = (BigDecimal) linha.getCampo("CODITEM");
            controleOsRepository.atualizarStatusOSByPK(codOs, StatusOS.FECHADA.getCodigo());
            List<Componente> componenteList = partnameRepository.gerarComponentesDoPartname(codOs);
            for (Componente componente : componenteList) {
                componente.setCodProd(codProd);
                componentesRepository.salvarComponente(componente);
            }

        }
    }
}
