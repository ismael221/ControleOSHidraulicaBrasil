package br.com.bolt.controle.os.actions;

import br.com.bolt.controle.os.service.ComposicaoService;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.modelcore.facades.BiaAssistantSP;

import java.math.BigDecimal;

public class AcaoAssociarComponentes implements AcaoRotinaJava {
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {

        ComposicaoService composicaoService = new ComposicaoService();

        Registro[] linhas = contexto.getLinhas();
        for (Registro linha : linhas) {
            BigDecimal codOs = (BigDecimal) linha.getCampo("ID");
            BigDecimal item = (BigDecimal) linha.getCampo("CODITEM");
            composicaoService.associarComponenteAoPartname(codOs, item);
        }
    }
}
