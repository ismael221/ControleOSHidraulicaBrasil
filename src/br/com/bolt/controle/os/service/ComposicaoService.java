package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.Componente;
import br.com.bolt.controle.os.model.Partname;
import br.com.bolt.controle.os.repository.ComposicaoRepository;
import br.com.bolt.controle.os.repository.PartnameRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ComposicaoService {

    private final ComposicaoRepository composicaoRepository = new ComposicaoRepository();
    private final PartnameRepository partnameRepository = new PartnameRepository();

    public void associarComponenteAoPartname(BigDecimal codOs, BigDecimal item) {
        List<Componente> componenteList = composicaoRepository.listarComponentes(item);
        List<Partname> partnameList = partnameRepository.encontrarPartnamesPorOs(codOs);

        Map<BigDecimal, Componente> componenteMap = componenteList.stream()
                .collect(Collectors.toMap(Componente::getPartname, c -> c));

        for (Partname partname : partnameList) {
            Componente componente = componenteMap.get(partname.getPartname());
            if (componente != null) {
                BigDecimal codProd = componente.getCodComponente();
                System.out.println("Atualizando Partname " + partname.getPartname() + " com codProd " + codProd);

                partname.setCodProduto(codProd);
                partnameRepository.atualizarProdutoDoPartname(partname);
            }
        }


    }

}
