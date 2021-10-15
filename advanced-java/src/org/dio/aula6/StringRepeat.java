package org.dio.aula6;

public class StringRepeat {
    public static void main(String[] args) {
        String name = "Gabriel";
        String aux = "";

        for (int i = 0; i < 10; i++) {
            aux += name;
        }

//        System.out.println(aux);

        System.out.println(name.repeat(10));
    }
}
