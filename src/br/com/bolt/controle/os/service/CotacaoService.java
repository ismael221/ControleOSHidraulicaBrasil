package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.model.AgrupadoDTO;
import br.com.bolt.controle.os.model.MaterialCotacaoDTO;
import br.com.bolt.controle.os.repository.CotacaoRepository;
import br.com.bolt.controle.os.util.Utils;
import br.com.sankhya.jape.core.JapeSession;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CotacaoService {

    JapeSession.SessionHandle hnd = null;

    private final CotacaoRepository cotacaoRepository = new CotacaoRepository();

    public void inserirMaterialCotacoes(BigDecimal nunota, BigDecimal codCot) {
        List<MaterialCotacaoDTO> materiais = cotacaoRepository.buscarItensParaCotacao(nunota);

        // Agrupa por CODPARTNAME
        Map<BigDecimal, AgrupadoDTO> agrupado = materiais.stream()
                .collect(Collectors.toMap(
                        MaterialCotacaoDTO::getCodPartname, // chave: codPartname
                        m -> new AgrupadoDTO(
                                m.getCodPartname(),
                                m.getDescr(),
                                m.getUnidade(),
                                m.getQuantidade(),
                                new HashSet<>(Collections.singleton(m.getNuos()))
                        ),
                        (a, b) -> { // merge quando codPartname se repete
                            a.setQuantidade(a.getQuantidade().add(b.getQuantidade()));
                            a.getNuos().addAll(b.getNuos());
                            return a;
                        }
                ));

        // Converte para lista final
        List<AgrupadoDTO> resultado = new ArrayList<>(agrupado.values());

        // Exemplo de saída
        try {
            hnd = JapeSession.open();
            for (AgrupadoDTO dto : resultado) {
                System.out.println(dto);
                cotacaoRepository.salvarMaterialCotacao(dto, codCot);
            }
        } catch (Exception e) {
            Utils.logarErro(e);
        } finally {
            JapeSession.close(hnd);
        }
    }

}
