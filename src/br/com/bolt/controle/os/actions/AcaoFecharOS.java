package br.com.bolt.controle.os.actions;

import br.com.bolt.controle.os.enums.StatusOS;
import br.com.bolt.controle.os.model.Componente;
import br.com.bolt.controle.os.repository.ComposicaoRepository;
import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.repository.PartnameRepository;
import br.com.bolt.controle.os.util.Utils;
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
        ComposicaoRepository composicaoRepository = new ComposicaoRepository();

        for (Registro linha : linhas) {
            BigDecimal codOs = (BigDecimal) linha.getCampo("ID");
            BigDecimal codProd = (BigDecimal) linha.getCampo("CODITEM");
            controleOsRepository.atualizarStatusOSByPK(codOs, StatusOS.FECHADA.getCodigo());
            List<Componente> componenteList = partnameRepository.gerarComponentesDoPartname(codOs);
            if (codProd != null && codProd.compareTo(BigDecimal.ZERO) != 0) {
                for (Componente componente : componenteList) {
                    componente.setCodProd(codProd);
                    composicaoRepository.salvarComponente(componente);
                }
            } else {
                System.out.println("Código do produto inválido: " + codProd);
                // ou lançar exceção:
                // throw new IllegalArgumentException("Código do produto inválido: " + codProd);
            }
        }
    }
}
