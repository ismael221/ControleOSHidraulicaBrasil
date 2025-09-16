package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.Partname;
import br.com.bolt.controle.os.repository.PartnameRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PartnameService {

    private final PartnameRepository partnameRepository = new PartnameRepository();

    public void inserirPartnamesObrigatorios(BigDecimal codOs) {
        List<Partname> partnameList = new ArrayList();

        Partname partname94 = new Partname(new BigDecimal(300010000), new BigDecimal(94), BigDecimal.ONE);
        Partname partname95 = new Partname(new BigDecimal(300020000), new BigDecimal(95), BigDecimal.ONE);
        Partname partname96 = new Partname(new BigDecimal(300030000), new BigDecimal(96), BigDecimal.ONE);
        Partname partname97 = new Partname(new BigDecimal(300040000), new BigDecimal(97), BigDecimal.ONE);
        Partname partname98 = new Partname(new BigDecimal(300050000), new BigDecimal(98), BigDecimal.ONE);
        Partname partname99 = new Partname(new BigDecimal(300060000), new BigDecimal(99), BigDecimal.ONE);
        Partname partname100 = new Partname(new BigDecimal(300070000), new BigDecimal(100), BigDecimal.ONE);
        Partname partname101 = new Partname(new BigDecimal(300080000), new BigDecimal(101), BigDecimal.ONE);

        partnameList.add(partname94);
        partnameList.add(partname95);
        partnameList.add(partname96);
        partnameList.add(partname97);
        partnameList.add(partname98);
        partnameList.add(partname99);
        partnameList.add(partname100);
        partnameList.add(partname101);

        for (Partname partname : partnameList) {
            partnameRepository.inserirPartname(partname, codOs);
        }
    }
}
