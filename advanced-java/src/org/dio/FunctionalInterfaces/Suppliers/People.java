package org.dio.FunctionalInterfaces.Suppliers;

public class People {

    private String name;
    private Integer age;

    public People() {
        this.name = "Gabriel";
        this.age = 25;
    }

    @Override
    public String toString() {
        return String.format("name: %s, age: %d", this.name, this.age);
    }
}
