package br.com.bolt.controle.os.view;

import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.modelcore.exportadorrelatorio.RelatorioFormatado;

import java.math.BigDecimal;

public class AcaoAprovada implements AcaoRotinaJava {
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        Registro[] linhas = contexto.getLinhas();
        ControleOsRepository controleOsRepository = new ControleOsRepository();
        for (Registro linha : linhas) {
            BigDecimal codOs = (BigDecimal) linha.getCampo("ID");
            controleOsRepository.atualizarStatusOSByPK(codOs, new BigDecimal(7));

        }
    }
}
