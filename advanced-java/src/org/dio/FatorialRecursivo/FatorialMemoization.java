package org.dio.FatorialRecursivo;

import java.util.HashMap;
import java.util.Map;

public class FatorialMemoization {

    static Map<Integer, Integer> MAPA_FATORIAL = new HashMap<>();

    public static void main(String[] args) {
        long I = System.nanoTime();
        System.out.println(fatorialComMemoization(15));
        long F = System.nanoTime();
        System.out.println("Fatorial 1 " + (F - I));

        I = System.nanoTime();
        System.out.println(fatorialComMemoization(15));
        F = System.nanoTime();
        System.out.println("Fatorial 2 " + (F - I));
    }

    private static Integer fatorialComMemoization(Integer value) {
        if (value == 1) return value;
        if (MAPA_FATORIAL.containsKey(value)) {
            return MAPA_FATORIAL.get(value);
        } else {
            Integer result = value * fatorialComMemoization(value - 1);
            return result;
        }
    }
}
