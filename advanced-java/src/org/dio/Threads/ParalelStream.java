package org.dio.Threads;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ParalelStream {

    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();
        IntStream.range(1, 10000).parallel().forEach(ParalelStream::factorial);
//        IntStream.range(1, 20).parallel().forEach(num -> factorial(num));
        long fim = System.currentTimeMillis();

        System.out.println("execution time " + (fim - inicio));

        List<String> names = Arrays.asList("Gabriel", "Domene");
        names.stream().forEach(System.out::println);
    }

    public static long factorial(long number) {
        int fat = 1;

        for (int i = 2; i <= number; i++) {
            fat *= i;
        }
        System.out.println(fat);
        return fat;
    }
}
