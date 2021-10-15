package org.dio.Inferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class Inference {

    public static void main(String[] args) throws IOException {
//        connectAndPrintJavaURL();
//        printCompleteName("Gabriel", "Domene");
        printSum(10, 12, 7);
    }

    private static void connectAndPrintJavaURL() throws IOException {
        URL url = new URL("https://docs.oracle.com/javase/10/language");
        URLConnection urlConn = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        System.out.println(bufferedReader.lines().collect(Collectors.joining())
                .replaceAll(">", ">\n"));
    }

    public static void printCompleteName(String name, String lastName) {
        System.out.println(String.format("%s %s", name, lastName));
    }

    public static void printSum(int... numbers) {
        int sum;
        if (numbers.length > 0) {
            sum = 0;
            for (int num : numbers) {
                sum += num;
            }
            System.out.println(sum);
        }
    }

}
