package org.dio.aula6;

import java.util.function.Function;

public class LambdaInference {
    public static void main(String[] args) {
        Function<Integer, Double> divisionByTwo = (Integer number) -> (double) (number / 2);
        System.out.println("Division " + divisionByTwo.apply(8));
    }
}
