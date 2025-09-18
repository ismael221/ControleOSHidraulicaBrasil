package br.com.bolt.controle.os.service;

import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.util.JapeSessionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.PrePersistEntityState;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.comercial.BarramentoRegra;
import br.com.sankhya.modelcore.comercial.centrais.CACHelper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class CabecalhoNotaService {

    public static AuthenticationInfo authInfo;

    public BigDecimal criarCabecalhoNota() throws Exception {
        EntityFacade dwf = EntityFacadeFactory.getDWFFacade();
        NativeSql nativeSql = new NativeSql(dwf.getJdbcWrapper());
        authInfo = new AuthenticationInfo("SUP", BigDecimal.ZERO, BigDecimal.ZERO, 0);

        BigDecimal nunota = null;
        DynamicVO novoCabVO = (DynamicVO) dwf.getDefaultValueObjectInstance("CabecalhoNota");

//        System.out.println("CODTIPOPER: " + codTop);
//        novoCabVO.setProperty("CODTIPOPER", codTop);
//
//        System.out.println("CODUSUINC: " + codUsu);
//        novoCabVO.setProperty("CODUSUINC", codUsu);
//
//        System.out.println("CODEMP: " + codEmpAgreg);
//        novoCabVO.setProperty("CODEMP", codEmpAgreg);
//
//        System.out.println("CODPARC: " + codParcForn);
//        novoCabVO.setProperty("CODPARC", codParcForn);
//
//        System.out.println("CODPROJ: " + codproj);
//        novoCabVO.setProperty("CODPROJ", codproj);
//
//        System.out.println("NUMNOTA: " + BigDecimal.ZERO);
//        novoCabVO.setProperty("NUMNOTA", BigDecimal.ZERO);
//
//
//        System.out.println("CODNAT: " + natureza);
//        novoCabVO.setProperty("CODNAT", natureza);

        Timestamp agora = new Timestamp(System.currentTimeMillis());
        System.out.println("DTNEG: " + agora);
        novoCabVO.setProperty("DTNEG", agora);

        System.out.println("TIPMOV: O");
        novoCabVO.setProperty("TIPMOV", "O");


        PrePersistEntityState cabPreState = PrePersistEntityState.build(dwf, "CabecalhoNota", novoCabVO, null, null);

        JapeSessionContext.putProperty("br.com.sankhya.com.CentralCompraVenda", Boolean.TRUE);

        CACHelper cacHelper = new CACHelper();
        BarramentoRegra bRegrasCab = cacHelper.incluirAlterarCabecalho(authInfo, cabPreState);

        DynamicVO newCabVO = bRegrasCab.getState().getNewVO();
        nunota = newCabVO.asBigDecimal("NUNOTA");
        System.out.println("nunota: " + nunota);

        System.out.println("Cabeçalho criado com sucesso. NUNOTA: " + nunota);

        return nunota;
    }

    public void incluirItensCabecalhoNota(BigDecimal nunota) throws Exception {
        ArrayList<PrePersistEntityState> itensNota = new ArrayList<>();
        EntityFacade dwf = EntityFacadeFactory.getDWFFacade();
        DynamicVO itemVO = (DynamicVO) dwf.getDefaultValueObjectInstance("ItemNota");
        authInfo = new AuthenticationInfo("SUP", BigDecimal.ZERO, BigDecimal.ZERO, 0);
        CACHelper cacHelper = new CACHelper();

        //itemVO.setPrimaryKey(null);
        System.out.println("NUNOTA: " + nunota);
        itemVO.setProperty("NUNOTA", nunota);

//        System.out.println("SEQUENCIA: " + sequencia);
//        itemVO.setProperty("SEQUENCIA", sequencia);
//
//        BigDecimal codProd = rs2.getBigDecimal("CODPROD");
//        System.out.println("CODPROD: " + codProd);
//        itemVO.setProperty("CODPROD", codProd);
//
//        BigDecimal codLocal = rs2.getBigDecimal("CODLOCAL");
//        System.out.println("CODLOCALORIG: " + codLocal);
//        itemVO.setProperty("CODLOCALORIG", codLocal);
//
//        String controle = rs2.getString("CONTROLE");
//        System.out.println("CONTROLE: " + controle);
//        itemVO.setProperty("CONTROLE", controle);
//
//        String codVol = rs2.getString("CODVOL");
//        System.out.println("CODVOL: " + codVol);
//        itemVO.setProperty("CODVOL", codVol);


        PrePersistEntityState itemMontado = PrePersistEntityState.build(dwf, "ItemNota", itemVO);
        itensNota.add(itemMontado);
        ArrayList<PrePersistEntityState> itensUnitario = new ArrayList<>();
        itensUnitario.add(itemMontado);
        cacHelper.incluirAlterarItem(nunota, authInfo, itensUnitario, true);

    }
}
