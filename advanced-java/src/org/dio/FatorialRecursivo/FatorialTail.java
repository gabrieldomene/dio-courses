package org.dio.FatorialRecursivo;

public class FatorialTail {

    public static void main(String[] args) {
        System.out.println(fatorialA(5));
    }

    public static double fatorialA(double value) {
        return fatorialComTailCall(value, 1);
    }

    public static double fatorialComTailCall(double value, double number) {
        if (value == 0) return number;
        return fatorialComTailCall(value - 1, number * value);
    }
}
