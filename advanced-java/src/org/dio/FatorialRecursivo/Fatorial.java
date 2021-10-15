package org.dio.FatorialRecursivo;

public class Fatorial {

    public static void main(String[] args) {

        System.out.println(fatorial(0));
    }

    public static int fatorial(int value) {
        if (value == 1) return 1;
        return value * fatorial(value - 1);
    }
}
