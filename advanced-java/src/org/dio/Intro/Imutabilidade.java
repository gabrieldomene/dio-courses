package org.dio.Intro;

import java.util.function.UnaryOperator;

public class Imutabilidade {

    public static void main(String[] args) {

        int value = 20;
        UnaryOperator<Integer> returnDouble = v -> v * 2;
        System.out.println(returnDouble.apply(value));
        System.out.println(value);
    }
}
