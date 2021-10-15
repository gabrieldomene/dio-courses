package org.dio.Intro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComposicaoDeFuncoes {

    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4};

//        Arrays.stream(values)
//                .filter(number -> number % 2 == 0)
//                .map(number -> number * 2)
//                .forEach(number -> System.out.println(number));
        for (int i = 0; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                int result = values[i] * 2;
                System.out.println(result);
            }
        }
    }
}
