package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.Partname;
import br.com.bolt.controle.os.repository.PartnameRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PartnameService {

    private final PartnameRepository partnameRepository = new PartnameRepository();

    public void inserirPartnamesObrigatorios(List<Partname> partnameList, BigDecimal codOs) {
        for (Partname partname : partnameList) {
            partnameRepository.inserirPartname(partname, codOs);
        }
    }
}
