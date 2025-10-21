package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.Componente;
import br.com.bolt.controle.os.model.Partname;
import br.com.bolt.controle.os.repository.ComposicaoRepository;
import br.com.bolt.controle.os.repository.PartnameRepository;

import java.math.BigDecimal;
import java.util.List;

public class ComposicaoService {

    private final ComposicaoRepository composicaoRepository = new ComposicaoRepository();
    private final PartnameRepository partnameRepository = new PartnameRepository();

    public void associarComponenteAoPartname(BigDecimal codOs,BigDecimal item){
        List<Componente> componenteList = composicaoRepository.listarComponentes(item);
        List<Partname> partnameList = partnameRepository.encontrarPartnamesPorOs(codOs);

        componenteList.stream()
                .filter(c -> partnameList.stream()
                        .anyMatch(p -> p.getPartname().equals(c.getPartname())))
                .forEach(c -> {
                    System.out.println("Partname igual: " + c.getPartname());
                    // executarAcao(c);
                });
    }

}
