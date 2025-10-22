package br.com.bolt.controle.os.actions;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.modelcore.util.AgendamentoRelatorioHelper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import br.com.sankhya.ws.ServiceContext;
import com.sankhya.util.SessionFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AcaoImprimirOrcamento implements AcaoRotinaJava {
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        BigDecimal nuRfe = new BigDecimal(179);
        List<Object> lstParam = new ArrayList<Object>();
        byte[] pdfBytes = null;
        String chave = "chave.pdf";
        Registro[] regs = contexto.getLinhas();
        if (regs.length == 0) {
            throw new Exception("Selecione um registro");
        }

        try {
            EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

            AgendamentoRelatorioHelper.ParametroRelatorio pk = new AgendamentoRelatorioHelper.ParametroRelatorio("PK_NUNOTA", BigDecimal.class.getName(), regs[0].getCampo("NUNOTA"));
            AgendamentoRelatorioHelper.ParametroRelatorio logo = new AgendamentoRelatorioHelper.ParametroRelatorio("PDIR_MODELO", String.class.getName(), "/home/mgeweb/repositorio/System/Imagens/");

            lstParam.add(pk);
            lstParam.add(logo);

            pdfBytes = AgendamentoRelatorioHelper.getPrintableReport(nuRfe, lstParam, contexto.getUsuarioLogado(), dwfFacade);

            SessionFile sessionFile = SessionFile.createSessionFile("avaliacao.pdf", "teste", pdfBytes);
            ServiceContext.getCurrent().putHttpSessionAttribute(chave, sessionFile);

            contexto.setMensagemRetorno("<a id=\"alink\" href=\"/mge/visualizadorArquivos.mge?chaveArquivo="
                    + chave
                    + "\" target=\"_blank\">Baixar Arquivo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
