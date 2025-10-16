package br.com.bolt.controle.os.actions;

import br.com.bolt.controle.os.enums.StatusOS;
import br.com.bolt.controle.os.model.Partname;
import br.com.bolt.controle.os.model.PedidoCompra;
import br.com.bolt.controle.os.model.PedidoRequisicao;
import br.com.bolt.controle.os.repository.ControleOsRepository;
import br.com.bolt.controle.os.service.PartnameService;
import br.com.bolt.controle.os.service.PedidoService;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AcaoAprovada implements AcaoRotinaJava {
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        Registro[] linhas = contexto.getLinhas();
        ControleOsRepository controleOsRepository = new ControleOsRepository();
        PartnameService partnameService = new PartnameService();
        PedidoService pedidoService = new PedidoService();

        for (Registro linha : linhas) {
            BigDecimal codOs = (BigDecimal) linha.getCampo("ID");
            String nuOs = (String) linha.getCampo("NUOS");
            BigDecimal codParc =  (BigDecimal) linha.getCampo("CODPARC");

            controleOsRepository.atualizarStatusOSByPK(codOs, StatusOS.APROVADA.getCodigo());

            controleOsRepository.aprovarOS(codOs);

            List<Partname> partnameList = new ArrayList();

            Partname partname98 = new Partname(new BigDecimal(209990004), new BigDecimal(95), BigDecimal.ONE, new BigDecimal(6));
            Partname partname99 = new Partname(new BigDecimal(209990005), new BigDecimal(96), BigDecimal.ONE, new BigDecimal(6));
            Partname partname100 = new Partname(new BigDecimal(209990006), new BigDecimal(97), BigDecimal.ONE, new BigDecimal(6));

            partnameList.add(partname98);
            partnameList.add(partname99);
            partnameList.add(partname100);

            partnameService.inserirPartnamesObrigatorios(partnameList, codOs);

            List<BigDecimal> revisoesAnteriores = controleOsRepository.encontrarOsPorNuOS(nuOs);
            for (BigDecimal revisao : revisoesAnteriores) {
                if (revisao.compareTo(codOs) != 0) {
                    controleOsRepository.atualizarStatusEreprovarOs(revisao, StatusOS.FECHADA_APROVADA_EM_OUTRA_REVISAO.getCodigo());
                }
            }

            PedidoCompra pedidoCompra = new PedidoCompra();
            pedidoCompra.setCodCenCus(BigDecimal.ZERO);
            pedidoCompra.setCodEmp(BigDecimal.ONE);
            pedidoCompra.setCodTipOper(new BigDecimal(1001));
            pedidoCompra.setCodUsu(BigDecimal.ZERO);
            pedidoCompra.setCodTipVenda(new BigDecimal(13));
            pedidoCompra.setCodParc(codParc);
            pedidoCompra.setTipMov("P");
            pedidoService.gerarPedido(pedidoCompra,codOs);


            PedidoRequisicao pedidoRequisicao = new PedidoRequisicao();
            pedidoRequisicao.setCodCenCus(BigDecimal.ZERO);
            pedidoRequisicao.setCodEmp(BigDecimal.ONE);
            pedidoRequisicao.setCodTipOper(new BigDecimal(1801));
            pedidoRequisicao.setCodUsu(BigDecimal.ZERO);
            pedidoRequisicao.setCodTipVenda(new BigDecimal(13));
            pedidoRequisicao.setCodParc(codParc);
            pedidoRequisicao.setTipMov("J");
            pedidoService.gerarPedidoRequisicao(pedidoRequisicao,codOs);
        }
    }
}
