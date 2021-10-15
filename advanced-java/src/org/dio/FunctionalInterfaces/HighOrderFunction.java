package org.dio.FunctionalInterfaces;

public class HighOrderFunction {

    public static void main(String[] args) {

        Calculate sum = (a, b) -> a + b;
        Calculate subtract = (a, b) -> a - b;
        Calculate divide = (a, b) -> a / b;
        Calculate multiplicate = (a, b) -> a * b;

        System.out.println(executeOperation(sum, 1, 3));
        System.out.println(executeOperation(subtract, 4, 3));
        System.out.println(executeOperation(divide, 9, 3));
        System.out.println(executeOperation(multiplicate, 4, 3));
    }

    public static int executeOperation(Calculate calc, int a, int b) {
        return calc.sum(a, b);
    }

}

interface Calculate {
    public int sum(int a, int b);
}
