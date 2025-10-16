package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.HistoricoMovimento;
import br.com.bolt.controle.os.model.PedidoCompra;
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

public class PedidoService {

    public static AuthenticationInfo authInfo;

    public BigDecimal gerarPedido(PedidoCompra pedidoCompra, BigDecimal idOs) {
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

            HistoricoMovimento historicoMovimento = new HistoricoMovimento(
                    idOs,
                    Timestamp.from(Instant.now()),
                    pedidoCompra.getTipMov(),
                    pedidoCompra.getCodTipOper(),
                    pedidoCompra.getVlrTotal()
            );

            historicoMovimentoRepository.salvarMovimento(historicoMovimento);

            System.out.println("Cabeçalho criado com sucesso. NUNOTA: " + nunota);
        } catch (Exception e) {
            Utils.logarErro(e);
        }
        return nunota;
    }
}
