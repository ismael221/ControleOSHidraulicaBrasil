package br.com.bolt.controle.os.view;

import br.com.bolt.controle.os.model.Partname;
import br.com.bolt.controle.os.repository.PartnameRepository;
import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

import java.math.BigDecimal;
import java.util.List;

public class EventControleOS implements EventoProgramavelJava {
    @Override
    public void beforeInsert(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeUpdate(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent event) throws Exception {
        System.out.println("EventControleOs.afterInsert");
        PartnameRepository partnameRepository = new PartnameRepository();
        DynamicVO cabecalhoOSVo = (DynamicVO) event.getVo();
        BigDecimal macrogrupo = cabecalhoOSVo.asBigDecimal("CODMACROGRP");
        BigDecimal codOs = cabecalhoOSVo.asBigDecimal("ID");

        if (macrogrupo != null && macrogrupo.compareTo(BigDecimal.ZERO) != 0) {
            List<Partname> partnames = partnameRepository.encontrarPartnames(macrogrupo);
            for (Partname partname : partnames) {
                partnameRepository.inserirPartname(partname, codOs);
            }
        }

    }

    @Override
    public void afterUpdate(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext tranCtx) throws Exception {

    }
}
