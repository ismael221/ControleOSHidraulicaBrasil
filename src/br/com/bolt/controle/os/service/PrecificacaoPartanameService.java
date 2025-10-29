package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.Partname;
import br.com.bolt.controle.os.repository.PrecoRepository;

import java.math.BigDecimal;

public class PrecificacaoPartanameService {

    private final PrecoRepository precoRepository = new PrecoRepository();
    private final PartnameService partnameService = new PartnameService();

    public void atualizarPrecosPartname(int tipNota, BigDecimal codOs, BigDecimal codPartname) {
        System.out.println("Inicio atualização de preço ...");
        Double custoTotServico = precoRepository.custoTotalServico(codOs, codPartname);
        Double markup = precoRepository.markupServico(codOs);
        Double totalVendaServico = custoTotServico * markup;
        Double totalVendaMaterial = precoRepository.totalVendaMaterial(codOs, codPartname);
        Double percentualTaxa = precoRepository.taxa() / 100;
        Double taxaAdicionalServico = tipNota == 1 ? percentualTaxa * totalVendaMaterial : 0.0;
        Double totalDeVenda = totalVendaServico + totalVendaMaterial + taxaAdicionalServico;

        Partname partnameAtualizado = new Partname();
        partnameAtualizado.setCodOs(codOs);
        partnameAtualizado.setCodPartname(codPartname);
        partnameAtualizado.setTotalVendaMaterial(totalVendaMaterial);
        partnameAtualizado.setTotalVendaServico(totalVendaServico);
        partnameAtualizado.setCustoTotalServico(custoTotServico);
        partnameAtualizado.setTaxaAdicionalServico(taxaAdicionalServico);
        partnameAtualizado.setTotalVenda(totalDeVenda);

        System.out.println("Preço atualizado : " + partnameAtualizado.toString());
        partnameService.atualizarPrecos(partnameAtualizado);
    }
}
