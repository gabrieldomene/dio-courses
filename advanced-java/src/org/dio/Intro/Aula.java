package org.dio.Intro;

public class Aula {
    public static void main(String[] args) {

        Funcao1 funcao1 = valor -> System.out.println("Sr " + valor);
        funcao1.gerar("Gabriel");
    }
}

@FunctionalInterface
interface Funcao1 {
    void gerar(String valor);
}
