package org.dio.FunctionalInterfaces.Suppliers;

import java.util.function.Supplier;

public class Suppliers {

    public static void main(String[] args) {

        Supplier<People> suppliers = People::new;

        System.out.println(suppliers.get());
    }
}
