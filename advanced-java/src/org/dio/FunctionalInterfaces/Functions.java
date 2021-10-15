package org.dio.FunctionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Functions {

    public static void main(String[] args) {
        Function<String, String> returnBackwardsName =
                text -> new StringBuilder(text).reverse().toString();
        Function<String, Integer> convertStringToIntegerAndCalculateDouble =
                string -> Integer.parseInt(string) * 2;
        System.out.println(convertStringToIntegerAndCalculateDouble.apply("20"));
        System.out.println(returnBackwardsName.apply("Gabriel Domene"));
    }
}
