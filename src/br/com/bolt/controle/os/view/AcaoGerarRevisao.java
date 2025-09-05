package br.com.bolt.controle.os.view;

import br.com.bolt.controle.os.model.OrdemDeServico;
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
        String justificativa = contexto.getParam("MOTIVO").toString();
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

            Registro os = contexto.novaLinha("AD_CONTROLEOS");
            os.setCampo("REVISAO", ordemDeServico.getRevisao());
            os.setCampo("NUOS", ordemDeServico.getNuOS());
            os.setCampo("DESCR", ordemDeServico.getDescricaoProblema());
            os.setCampo("DESCRNOTA", ordemDeServico.getDescricaoParaNota());
            os.setCampo("CODEMP", ordemDeServico.getEmpresa());
            os.setCampo("CODEQUIP", ordemDeServico.getEquipamento());
            os.setCampo("CODEMACROGRP", ordemDeServico.getMacrogrupo());
            os.setCampo("NUNOTA", ordemDeServico.getOrcamento());
            os.setCampo("CODPARC", ordemDeServico.getParceiro());
            os.setCampo("CODEMP", ordemDeServico.getOrcamento());
            os.setCampo("REVISAO", ordemDeServico.getRevisao().add(BigDecimal.ONE));
            os.save();

            StringBuffer mensagem = new StringBuffer();
            mensagem.append("Revisao realizado com sucesso!");
            mensagem.append("\n");
            mensagem.append("Justificativa: " + justificativa);
            mensagem.append("\n");
            mensagem.append("OS: " + os.getCampo("NUOS"));
            mensagem.append("\n");

            contexto.setMensagemRetorno(mensagem.toString());
        }
    }
}
