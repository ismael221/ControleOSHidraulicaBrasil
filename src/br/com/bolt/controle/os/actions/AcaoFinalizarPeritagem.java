package br.com.bolt.controle.os.actions;

import br.com.bolt.controle.os.enums.StatusOS;
import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.repository.CotacaoRepository;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;

public class AcaoFinalizarPeritagem implements AcaoRotinaJava {
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        Registro[] linhas = contexto.getLinhas();
        ControleOsRepository controleOsRepository = new ControleOsRepository();
        CotacaoRepository cotacaoRepository = new CotacaoRepository();
        for (Registro linha : linhas) {
            BigDecimal codOs = (BigDecimal) linha.getCampo("ID");
            controleOsRepository.atualizarStatusOSByPK(codOs, StatusOS.COTACAO.getCodigo());
//            System.out.println("Gerando Cotação ...");
//            ArrayList<Cotacao> cotacoes = cotacaoRepository.encontrarCotacoes(codOs);
//
//            for (Cotacao cotacao : cotacoes) {
//                System.out.println("Cotação gerada: " + cotacao.toString());
//                cotacaoRepository.salvarCotacao(cotacao);
//            }
        }
    }
}
