package br.com.bolt.controle.os.service;

import br.com.bolt.controle.os.repository.NumeroOsRepository;

import java.time.Year;
import java.util.Map;

public class OSNumeroGeneratorService {

    private static final NumeroOsRepository numeroOsRepository = new NumeroOsRepository();

    public static synchronized String gerarNumero(String letra) {
        int ano = Year.now().getValue();

        Map<Integer, Integer> sequencias = numeroOsRepository.consultarUltimoNumero();

        System.out.println("Letra: " + letra);
        System.out.println("Ano: " + ano);
        System.out.println("Sequencias: " + sequencias.toString());
        System.out.println("Chave " + ano + letra);

        int ultimoNumero = sequencias.getOrDefault(ano, 0);

        ultimoNumero++;

        return ano + letra + String.format("%06d", ultimoNumero);
    }

}
