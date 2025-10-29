package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.Partname;
import br.com.bolt.controle.os.repository.PartnameRepository;
import br.com.bolt.controle.os.repository.ServicosRepository;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.core.JapeSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PartnameService {

    JapeSession.SessionHandle hnd = null;

    private final PartnameRepository partnameRepository = new PartnameRepository();
    private final ServicosRepository servicosRepository = new ServicosRepository();

    public void inserirPartnamesObrigatorios(List<Partname> partnameList, BigDecimal codOs) {
        BigDecimal retorno = BigDecimal.ZERO;
        List<BigDecimal> retornos = new ArrayList<>();
        try {
            hnd = JapeSession.open();
            for (Partname partname : partnameList) {
                retorno = partnameRepository.inserirPartname(partname, codOs);
                BigDecimal servico = servicosRepository.resgatarServicoDoPartname(partname.getPartname()) != null
                        ? servicosRepository.resgatarServicoDoPartname(partname.getPartname())
                        : BigDecimal.ZERO;

                System.out.println("Vai tentar lançar serviço para o partname: " + partname + " - " + servico);
                servicosRepository.lancarServico(codOs, retorno, servico);
                retornos.add(retorno);
            }

        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }

    }

    public BigDecimal lancarPartname(Partname partname, BigDecimal codOs) {
        BigDecimal retorno = BigDecimal.ZERO;
        try {
            hnd = JapeSession.open();
            retorno = partnameRepository.inserirPartname(partname, codOs);
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }

        return retorno;
    }

    public void atualizarPrecos(Partname partname) {
        System.out.println("Service::atualizarPrecos " + partname.toString());
        try {
            hnd = JapeSession.open();
            partnameRepository.atualizarPrecoPartname(partname);
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }
}
