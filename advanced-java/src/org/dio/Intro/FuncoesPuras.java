package org.dio.Intro;

import java.util.function.BiPredicate;

public class FuncoesPuras {

    public static void main(String[] args) {

        BiPredicate<Integer, Integer> ifBigger =
                (parameter, valueComparison) -> parameter > valueComparison;

        System.out.println(ifBigger.test(13, 12));
        System.out.println(ifBigger.test(13, 12));
    }
}
