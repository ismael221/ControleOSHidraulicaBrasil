package br.com.bolt.controle.os.service;

import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.comercial.util.print.PrintManager;
import br.com.sankhya.modelcore.comercial.util.print.converter.PrintConversionService;
import br.com.sankhya.modelcore.comercial.util.print.model.PrintInfo;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import br.com.sankhya.modelcore.util.Report;
import br.com.sankhya.modelcore.util.ReportManager;
import br.com.sankhya.sps.enumeration.DocTaste;
import br.com.sankhya.sps.enumeration.DocType;
import net.sf.jasperreports.engine.JasperPrint;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ImpressaoService {

    public static void imprimir(BigDecimal romaneio, BigDecimal relatorio) throws Exception {
        EntityFacade dwfEntityFacade;
        JdbcWrapper jdbc = null;


        try {

            dwfEntityFacade = EntityFacadeFactory.getDWFFacade();
            jdbc = dwfEntityFacade.getJdbcWrapper();
            jdbc.openSession();

            Map<String, Object> param = new HashMap<>();
            //param.put("ROMANEIO", romaneio);

            Report report = ReportManager.getInstance().getReport(relatorio, dwfEntityFacade);

            JasperPrint jasperPrint = report.buildJasperPrint(param, jdbc.getConnection());

            byte[] conteudo = PrintConversionService.getInstance().convert(jasperPrint, byte[].class);
//            PrintManager printManager = PrintManager.getInstance();
//
//            String printerName = "?";
//            String jobDescription = "Impressão por job";
//            int copies = 1;
//
//            BigDecimal userId = AuthenticationInfo.getCurrent().getUserID();
//            String userName = "SUP";
//            BigDecimal codEmp = BigDecimal.ONE;
//            String idDocumento = "0";
//
//            PrintInfo printInfo = new PrintInfo(conteudo, DocTaste.JASPER, DocType.RELATORIO, printerName, jobDescription, copies, userId, userName, codEmp, idDocumento);
//
//            printManager.print(printInfo);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcWrapper.closeSession(jdbc);
        }

    }
}
