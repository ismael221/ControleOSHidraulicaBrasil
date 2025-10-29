package br.com.bolt.controle.os.actions;

import br.com.bolt.controle.os.service.PrecificacaoPartanameService;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;

public class AcaoPrecificarPartname implements AcaoRotinaJava {
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        Registro[] linhas = contexto.getLinhas();
        PrecificacaoPartanameService service = new PrecificacaoPartanameService();
        for (Registro linha : linhas) {
            BigDecimal tipNota = (BigDecimal) linha.getCampo("TIPNOTA");
            BigDecimal codOs = (BigDecimal) linha.getCampo("ID");
            BigDecimal codPartname = (BigDecimal) linha.getCampo("CODPARTNAME");

            service.atualizarPrecosPartname(Integer.parseInt(tipNota.toString()), codOs, codPartname);
        }
    }
}
