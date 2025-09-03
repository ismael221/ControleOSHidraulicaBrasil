package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.repository.ControleOsRepository;

import java.math.BigDecimal;

public class StatusOsService {

    public void atualizarStatusOs(BigDecimal codOS, BigDecimal statusOs) {
        ControleOsRepository controleOsRepository = new ControleOsRepository();
        controleOsRepository.atualizarStatusOSByPK(codOS,statusOs);
    }
}
