package org.dio.FunctionalInterfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Iterators {

    public static void main(String[] args) {

        String[] names = {"Gabriel", "Gabriel", "Joao", "Paulo", "Felipe", "Matheus"};
        Integer[] numbers = {28, 94, 86, 234, 123, 76};
//        printFilteredNames(names);
        printAllNames(names);
        printTheDoubleOfEachItemList(numbers);

        List<String> professionalList = new ArrayList<>();
        professionalList.add("developer");
        professionalList.add("manager");
        professionalList.add("product owner");
        professionalList.add("manager product");
        professionalList.add("quality assurance");

        professionalList.stream()
                .filter(worker -> worker.startsWith("manager"))
                .forEach(System.out::println);

    }

    private static void printFilteredNames(String... names) {
        String namesToPrint = "";
        for (String name : names) {
            if (name.equals("Gabriel")) {
                namesToPrint += " " + name;
            }
        }
        String namesToPrintFromStream = Stream.of(names)
                .filter(name -> name.equals("Gabriel"))
                .collect(Collectors.joining(" "));
//                .forEach(System.out::println);
        System.out.println("Names from FOR: " + namesToPrint);
        System.out.println("Names from stream " + namesToPrintFromStream);
    }

    public static void printAllNames(String... names) {
        for (String name : names) {
            System.out.println("Print for: " + name);
        }

        Stream.of(names)
                .forEach(name -> System.out.println("Print for each: " + name));
    }

    public static void printTheDoubleOfEachItemList(Integer... numbers) {
        for (Integer number : numbers) {
            System.out.println(number * 2);
        }

        Stream.of(numbers)
                .map(number -> number * 2)
                .forEach(System.out::println);
    }
}
