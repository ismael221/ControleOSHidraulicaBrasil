package br.com.bolt.controle.os.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {

    public static void logarErro(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        System.out.println("Erro : " + e.getMessage());
        System.out.println("Causa : " + e.getCause());
        e.printStackTrace(pw);
        System.out.println(sw.toString());
    }
}
