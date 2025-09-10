package br.com.bolt.controle.os.view;

import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.modelcore.exportadorrelatorio.RelatorioFormatado;

import java.math.BigDecimal;
import java.util.List;

public class AcaoAprovada implements AcaoRotinaJava {
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        Registro[] linhas = contexto.getLinhas();
        ControleOsRepository controleOsRepository = new ControleOsRepository();
        for (Registro linha : linhas) {
            BigDecimal codOs = (BigDecimal) linha.getCampo("ID");
            String nuOs = (String) linha.getCampo("NUOS");

            controleOsRepository.atualizarStatusOSByPK(codOs, new BigDecimal(7));

            controleOsRepository.aprovarOS(codOs);

            List<BigDecimal> revisoesAnteriores = controleOsRepository.encontrarOsPorNuOS(nuOs);
            for (BigDecimal revisao : revisoesAnteriores) {
                if (revisao.compareTo(codOs) != 0) {
                    controleOsRepository.atualizarStatusEreprovarOs(revisao, new BigDecimal(8));
                }
            }
        }
    }
}
