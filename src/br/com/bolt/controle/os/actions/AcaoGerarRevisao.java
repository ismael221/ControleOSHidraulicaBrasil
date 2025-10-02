package br.com.bolt.controle.os.actions;

import br.com.bolt.controle.os.model.OrdemDeServico;
import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.repository.RevisaoRepository;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.QueryExecutor;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;

public class AcaoGerarRevisao implements AcaoRotinaJava {
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        RevisaoRepository revisaoRepository = new RevisaoRepository();
        ControleOsRepository controleOsRepository = new ControleOsRepository();
        String justificativa = contexto.getParam("MOTIVO").toString();
        System.out.println("Justificativa da revisão: " + justificativa);
        Registro[] linhas = contexto.getLinhas();
        for (Registro linha : linhas) {
            OrdemDeServico ordemDeServico = new OrdemDeServico();
            BigDecimal codOs = (BigDecimal) linha.getCampo("ID");
            revisaoRepository.atualizarRevisao(codOs, justificativa);

            QueryExecutor query = contexto.getQuery();
            query.setParam("ID", codOs);
            query.nativeSelect("SELECT * FROM AD_CONTROLEOS WHERE ID = {ID}");
            while (query.next()) {
                ordemDeServico.setRevisao(query.getBigDecimal("REVISAO"));
                ordemDeServico.setNuOS(query.getString("NUOS"));
                ordemDeServico.setDescricaoProblema(query.getString("DESCR"));
                ordemDeServico.setDescricaoParaNota(query.getString("DESCRNOTA"));
                ordemDeServico.setOficina(query.getBigDecimal("OFICINA"));
                ordemDeServico.setParceiro(query.getBigDecimal("CODPARC"));
                ordemDeServico.setEmpresa(query.getBigDecimal("CODEMP"));
                ordemDeServico.setOrcamento(query.getBigDecimal("NUNOTA"));
                ordemDeServico.setEquipamento(query.getBigDecimal("CODEQUIP"));
                ordemDeServico.setMacrogrupo(query.getBigDecimal("CODMACROGRP"));
                ordemDeServico.setStatusOS(query.getBigDecimal("STATUS"));
            }

            query.close();

            controleOsRepository.criarNovaOs(ordemDeServico);

            StringBuffer mensagem = new StringBuffer();
            mensagem.append("Revisao realizado com sucesso!");
            mensagem.append("\n");
            mensagem.append("Justificativa: " + justificativa);
            mensagem.append("\n");

            contexto.setMensagemRetorno(mensagem.toString());
        }
    }
}
