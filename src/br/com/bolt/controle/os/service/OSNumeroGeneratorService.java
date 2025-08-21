package br.com.bolt.controle.os.service;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class OSNumeroGeneratorService {

    private static final Map<Integer, Integer> sequencias = new HashMap<>();

    public static synchronized String gerarNumero(String letra) {
        int ano = Year.now().getValue();

        int ultimoNumero = sequencias.getOrDefault(ano, 0);

        ultimoNumero++;

        sequencias.put(ano, ultimoNumero);

        return ano + letra + String.format("%06d", ultimoNumero);
    }

}
