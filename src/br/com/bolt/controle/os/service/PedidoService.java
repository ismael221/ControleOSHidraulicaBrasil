package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.*;
import br.com.bolt.controle.os.repository.HistoricoMovimentoRepository;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.util.JapeSessionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.PrePersistEntityState;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.comercial.BarramentoRegra;
import br.com.sankhya.modelcore.comercial.centrais.CACHelper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    public static AuthenticationInfo authInfo;

    public void gerarPedido(PedidoCompra pedidoCompra, BigDecimal idOs) {
        HistoricoMovimentoRepository historicoMovimentoRepository = new HistoricoMovimentoRepository();
        System.out.println("Iniciando o pedido");
        BigDecimal nunota = BigDecimal.ZERO;
        EntityFacade dwf = EntityFacadeFactory.getDWFFacade();
        authInfo = new AuthenticationInfo("SUP", BigDecimal.ZERO, BigDecimal.ZERO, 0);
        try {
            DynamicVO novoCabVO = (DynamicVO) dwf.getDefaultValueObjectInstance("CabecalhoNota");

            System.out.println("CODTIPOPER: " + pedidoCompra.getCodTipOper());
            novoCabVO.setProperty("CODTIPOPER", pedidoCompra.getCodTipOper());

            System.out.println("CODUSUINC: " + pedidoCompra.getCodUsu());
            novoCabVO.setProperty("CODUSUINC", pedidoCompra.getCodUsu());

            System.out.println("CODEMP: " + pedidoCompra.getCodEmp());
            novoCabVO.setProperty("CODEMP", pedidoCompra.getCodEmp());

            System.out.println("CODPARC: " + pedidoCompra.getCodParc());
            novoCabVO.setProperty("CODPARC", pedidoCompra.getCodParc());

            System.out.println("NUMNOTA: " + BigDecimal.ZERO);
            novoCabVO.setProperty("NUMNOTA", BigDecimal.ZERO);

            System.out.println("CODTIPVENDA: " + pedidoCompra.getCodTipVenda());
            novoCabVO.setProperty("CODTIPVENDA", pedidoCompra.getCodTipVenda());

            System.out.println("CODCENCUS: " + pedidoCompra.getCodCenCus());
            novoCabVO.setProperty("CODCENCUS", pedidoCompra.getCodCenCus());

            System.out.println("CODNAT: " + pedidoCompra.getCodNat());
            novoCabVO.setProperty("CODNAT", pedidoCompra.getCodNat());

            Timestamp agora = new Timestamp(System.currentTimeMillis());
            System.out.println("DTNEG: " + agora);
            novoCabVO.setProperty("DTNEG", agora);

            System.out.println("TIPMOV:" + pedidoCompra.getTipMov());
            novoCabVO.setProperty("TIPMOV", pedidoCompra.getTipMov());


            PrePersistEntityState cabPreState = PrePersistEntityState.build(dwf, "CabecalhoNota", novoCabVO, null, null);

            JapeSessionContext.putProperty("br.com.sankhya.com.CentralCompraVenda", Boolean.TRUE);

            CACHelper cacHelper = new CACHelper();
            BarramentoRegra bRegrasCab = cacHelper.incluirAlterarCabecalho(authInfo, cabPreState);

            DynamicVO newCabVO = bRegrasCab.getState().getNewVO();
            nunota = newCabVO.asBigDecimal("NUNOTA");
            System.out.println("nunota: " + nunota);

            List<ItemNota> itensNota = pedidoCompra.getItensNota();
            for (ItemNota itemNota : itensNota) {
                itemNota.setNunota(nunota);
                itemNota.setCodEmp(pedidoCompra.getCodEmp());
            }

            inserirItensNoPedido(nunota, itensNota);

            HistoricoMovimento historicoMovimento = new HistoricoMovimento(
                    idOs,
                    Timestamp.from(Instant.now()),
                    pedidoCompra.getTipMov(),
                    pedidoCompra.getCodTipOper(),
                    nunota,
                    pedidoCompra.getVlrTotal()
            );

            historicoMovimentoRepository.salvarMovimento(historicoMovimento);

            System.out.println("Cabeçalho criado com sucesso. NUNOTA: " + nunota);
        } catch (Exception e) {
            Utils.logarErro(e);
        }
    }

    public void gerarPedidoRequisicao(PedidoRequisicao pedidoRequisicao, BigDecimal idOs) {
        HistoricoMovimentoRepository historicoMovimentoRepository = new HistoricoMovimentoRepository();
        System.out.println("Iniciando o pedido");
        BigDecimal nunota = BigDecimal.ZERO;
        EntityFacade dwf = EntityFacadeFactory.getDWFFacade();
        authInfo = new AuthenticationInfo("SUP", BigDecimal.ZERO, BigDecimal.ZERO, 0);
        try {
            DynamicVO novoCabVO = (DynamicVO) dwf.getDefaultValueObjectInstance("CabecalhoNota");

            System.out.println("CODTIPOPER: " + pedidoRequisicao.getCodTipOper());
            novoCabVO.setProperty("CODTIPOPER", pedidoRequisicao.getCodTipOper());

            System.out.println("CODUSUINC: " + pedidoRequisicao.getCodUsu());
            novoCabVO.setProperty("CODUSUINC", pedidoRequisicao.getCodUsu());

            System.out.println("CODEMP: " + pedidoRequisicao.getCodEmp());
            novoCabVO.setProperty("CODEMP", pedidoRequisicao.getCodEmp());

            System.out.println("CODPARC: " + pedidoRequisicao.getCodParc());
            novoCabVO.setProperty("CODPARC", pedidoRequisicao.getCodParc());

            System.out.println("NUMNOTA: " + BigDecimal.ZERO);
            novoCabVO.setProperty("NUMNOTA", BigDecimal.ZERO);

            System.out.println("CODTIPVENDA: " + pedidoRequisicao.getCodTipVenda());
            novoCabVO.setProperty("CODTIPVENDA", pedidoRequisicao.getCodTipVenda());

            System.out.println("CODCENCUS: " + pedidoRequisicao.getCodCenCus());
            novoCabVO.setProperty("CODCENCUS", pedidoRequisicao.getCodCenCus());

            System.out.println("CODNAT: " + pedidoRequisicao.getCodNat());
            novoCabVO.setProperty("CODNAT", pedidoRequisicao.getCodNat());

            Timestamp agora = new Timestamp(System.currentTimeMillis());
            System.out.println("DTNEG: " + agora);
            novoCabVO.setProperty("DTNEG", agora);

            System.out.println("TIPMOV:" + pedidoRequisicao.getTipMov());
            novoCabVO.setProperty("TIPMOV", pedidoRequisicao.getTipMov());


            PrePersistEntityState cabPreState = PrePersistEntityState.build(dwf, "CabecalhoNota", novoCabVO, null, null);

            JapeSessionContext.putProperty("br.com.sankhya.com.CentralCompraVenda", Boolean.TRUE);

            CACHelper cacHelper = new CACHelper();
            BarramentoRegra bRegrasCab = cacHelper.incluirAlterarCabecalho(authInfo, cabPreState);

            DynamicVO newCabVO = bRegrasCab.getState().getNewVO();
            nunota = newCabVO.asBigDecimal("NUNOTA");
            System.out.println("nunota: " + nunota);


            List<ItemNota> itensNota = pedidoRequisicao.getItensNota();
            for (ItemNota itemNota : itensNota) {
                itemNota.setNunota(nunota);
                itemNota.setCodEmp(pedidoRequisicao.getCodEmp());
            }

            inserirItensNoPedido(nunota, itensNota);

            HistoricoMovimento historicoMovimento = new HistoricoMovimento(
                    idOs,
                    Timestamp.from(Instant.now()),
                    pedidoRequisicao.getTipMov(),
                    pedidoRequisicao.getCodTipOper(),
                    nunota,
                    pedidoRequisicao.getVlrTotal()
            );

            historicoMovimentoRepository.salvarMovimento(historicoMovimento);

            System.out.println("Cabeçalho criado com sucesso. NUNOTA: " + nunota);
        } catch (Exception e) {
            Utils.logarErro(e);
        }
    }

    public void inserirItensNoPedido(BigDecimal nunota, List<ItemNota> itensNota) throws Exception {
        EntityFacade dwf = EntityFacadeFactory.getDWFFacade();
        CACHelper cacHelper = new CACHelper();
        ArrayList<PrePersistEntityState> itensUnitario = new ArrayList<>();
        BigDecimal sequencia = BigDecimal.ONE;

        for (ItemNota item : itensNota) {
            DynamicVO itemVO = (DynamicVO) dwf.getDefaultValueObjectInstance("ItemNota");
            itemVO.setProperty("NUNOTA", nunota);
            itemVO.setProperty("SEQUENCIA", sequencia);
            itemVO.setProperty("CODPROD", item.getCodProd());
            itemVO.setProperty("QTDNEG", BigDecimal.valueOf(item.getQtdNeg()));
            itemVO.setProperty("VLRUNIT", BigDecimal.valueOf(item.getVlrUnit()));
            itemVO.setProperty("VLRTOT", BigDecimal.valueOf(item.getVlrTotal()));
            itemVO.setProperty("OBSERVACAO", item.getDescricao());

            PrePersistEntityState itemMontado = PrePersistEntityState.build(dwf, "ItemNota", itemVO);
            itensUnitario.add(itemMontado);
            sequencia = sequencia.add(BigDecimal.ONE);

        }
        cacHelper.incluirAlterarItem(nunota, authInfo, itensUnitario, true);
    }
}
